package com.example.raymond.androiddeveloperchallenge.modules.pinboard.presenter

import android.content.Context
import com.example.raymond.androiddeveloperchallenge.core.LocalSharedPreferences
import com.example.raymond.androiddeveloperchallenge.core.presenter.BasePresenter
import com.example.raymond.androiddeveloperchallenge.modules.pinboard.contract.PinBoardContract
import com.example.raymond.androiddeveloperchallenge.modules.pinboard.model.PinBoard
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PinBoardPresenter(activity: Context?) : BasePresenter(activity), PinBoardContract.Presenter {

    private val gson: Gson = Gson()

    override fun getRawData() {
        getApi()?.getRaw()?.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val pinBoard = gson.fromJson<Array<PinBoard>>(response.body(), Array<PinBoard>::class.java)
                getView()?.showRawData(pinBoard.asList())
                LocalSharedPreferences().getIns()?.saveListPinBoard(getContext(), Gson(), pinBoard)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                System.out.print(t.toString())
            }
        })
    }
}