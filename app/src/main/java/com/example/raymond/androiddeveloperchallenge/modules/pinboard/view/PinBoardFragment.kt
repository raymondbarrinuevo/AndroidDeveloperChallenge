package com.example.raymond.androiddeveloperchallenge.modules.pinboard.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
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

    override fun showRawData(raw: List<PinBoard>) {
        rcv_pin_board.layoutManager = LinearLayoutManager(context)
        rcv_pin_board.isNestedScrollingEnabled = false
        rcv_pin_board.adapter = PinBoardAdapter(context, raw)
        progressDialog?.hideProgress()
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