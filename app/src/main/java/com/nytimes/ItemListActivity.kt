package com.nytimes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.api.ApiClient
import com.nytimes.api.ApiInterface
import com.nytimes.dummy.DummyContent
import com.nytimes.pojo.MostViewedResponse
import com.nytimes.pojo.Result
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * An activity representing a list of Items.
 */
class ItemListActivity : AppCompatActivity() {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane = false
    private var apiInterface: ApiInterface? = null
    private var nytResultList: List<Result>? = null
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        listOfMostViewed
        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.title = title
        if (findViewById<View?>(R.id.item_detail_container) != null) { // The detail container view will be present only in the
// large-screen layouts (res/values-w900dp).
// If this view is present, then the
// activity should be in two-pane mode.
            mTwoPane = true
        }
        recyclerView = findViewById<View>(R.id.item_list) as RecyclerView
        assert(recyclerView != null)
    }

    /**
     * Get our list of most popular articles
     */
    val listOfMostViewed: Unit
        get() {
            apiInterface = ApiClient.client?.create(ApiInterface::class.java)
            val call: Call<MostViewedResponse?>? = apiInterface?.mostViewedList
            if (call != null) {
                call.enqueue(object : Callback<MostViewedResponse?> {
                    override fun onResponse(
                        call: Call<MostViewedResponse?>,
                        response: Response<MostViewedResponse?>
                    ) {
                        val nytResponse: MostViewedResponse? = response.body()
                        if (nytResponse != null) {
                            nytResultList = nytResponse.results
                            recyclerView!!.adapter = SimpleItemRecyclerViewAdapter(
                                nytResultList,
                                mTwoPane
                            )
                        } else {
                            Log.d("tag", response.toString())
                        }
                    }

                    override fun onFailure(
                        call: Call<MostViewedResponse?>,
                        t: Throwable
                    ) {
                        Log.d("tag", t.message)
                        call.cancel()
                    }
                })
            }
        }

    private class SimpleItemRecyclerViewAdapter internal constructor(
        items: List<Result>?,
        twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
        private val mArticles: List<Result>?
        private val mTwoPane: Boolean
        private val mOnClickListener =
            View.OnClickListener { view ->
                val result: Result = view.tag as Result
                val context = view.context
                val intent = Intent(context, ItemDetailActivity::class.java)
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, Gson().toJson(result))
                context.startActivity(intent)
            }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) {
            val result: Result = mArticles!![position]
            holder.mArticleTitle.setText(result.title)
            holder.mArticleAuthor.setText(result.byline)
            holder.mArticleDate.setText(result.published_date)
            if (result.media?.size!! > 0) {
                val url: String = result.media!![0].media_metadata?.get(0)?.url.toString()
                Log.d("thumbnail", url)
                Picasso.get().load(url).into(holder.mThumbnail)
            }
            holder.itemView.tag = mArticles[position]
            holder.itemView.setOnClickListener(mOnClickListener)
        }

        override fun getItemCount(): Int {
            return mArticles!!.size
        }

        internal inner class ViewHolder(view: View) :
            RecyclerView.ViewHolder(view) {
            val mArticleTitle: TextView
            val mArticleAuthor: TextView
            val mArticleDate: TextView
            val mThumbnail: ImageView

            init {
                mArticleTitle = view.findViewById(R.id.article_title)
                mArticleAuthor = view.findViewById(R.id.article_author)
                mArticleDate = view.findViewById(R.id.article_date)
                mThumbnail = view.findViewById(R.id.article_thumbnail)
            }
        }

        init {
            mArticles = items
            mTwoPane = twoPane
        }
    }
}