package com.nytimes

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nytimes.dummy.DummyContent
import com.nytimes.pojo.Result
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.gson.Gson


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
    : Fragment() {
    /**
     * The dummy content this fragment is presenting.
     */
    private var mItem: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = activity!!.intent.extras
        val jsonMyObject: String?
        if (extras != null) {
            jsonMyObject = extras.getString(ARG_ITEM_ID)
            mItem = Gson().fromJson<Result>(jsonMyObject, Result::class.java)
            val activity: Activity? = this.activity
            val appBarLayout =
                activity!!.findViewById<View>(R.id.toolbar_layout) as CollapsingToolbarLayout
            if (appBarLayout != null) {
                appBarLayout.title = mItem?.title
                //                appBarLayout.setBackgr
                appBarLayout.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            (rootView.findViewById<View>(R.id.item_detail) as TextView).setText(mItem?.abstruct)
        }
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
