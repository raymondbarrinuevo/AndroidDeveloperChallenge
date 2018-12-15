package com.example.raymond.androiddeveloperchallenge.modules.pinboard.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.customview.ProgressDialog
import com.example.raymond.androiddeveloperchallenge.core.view.BaseAppFragment
import com.example.raymond.androiddeveloperchallenge.modules.pinboard.adapter.PinBoardAdapter
import com.example.raymond.androiddeveloperchallenge.modules.pinboard.contract.PinBoardContract
import com.example.raymond.androiddeveloperchallenge.modules.pinboard.model.PinBoard
import com.example.raymond.androiddeveloperchallenge.modules.pinboard.presenter.PinBoardPresenter
import kotlinx.android.synthetic.main.fragment_pin_board.*


class PinBoardFragment : BaseAppFragment<PinBoardContract.Presenter>(), PinBoardContract.View {

    var progressDialog: ProgressDialog? = null
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private val lastVisibleItemPosition: Int get() = (rcv_pin_board.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pullToRefresh.setOnRefreshListener {
            progressDialog?.showProgress()
            presenter?.getRawData()
            pullToRefresh.isRefreshing = true
        }

        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView!!.layoutManager.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                  
                    rcv_pin_board.removeOnScrollListener(scrollListener)
                }
            }
        }
        rcv_pin_board.addOnScrollListener(scrollListener)
    }

    override fun showRawData(raw: List<PinBoard>) {
        rcv_pin_board.layoutManager = LinearLayoutManager(context)
        rcv_pin_board.isNestedScrollingEnabled = false
        rcv_pin_board.adapter = PinBoardAdapter(context, raw)
        progressDialog?.hideProgress()
        if (pullToRefresh.isRefreshing) {
            pullToRefresh.isRefreshing = false
        }
    }

    override fun createPresenterInstance(): PinBoardContract.Presenter {
        return PinBoardPresenter(context)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_pin_board
    }

    override fun initViews() {
        progressDialog = ProgressDialog(context)
        progressDialog?.showProgress()
        presenter?.getRawData()
    }
}