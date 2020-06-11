package com.eraqi.siatask.ui

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eraqi.siatask.R
import com.eraqi.siatask.Result
import com.eraqi.siatask.data.model.StackExchangeResponse
import com.eraqi.siatask.presentation.ResultsViewModel
import com.eraqi.siatask.presentation.factory.ResultsViewModelFactory
import com.eraqi.siatask.ui.adapter.StackOverflowResultsAdapter


/**
*this class represents the final view of our app which shows the result of the previous chat with the user, after getting the required paramters
 *
 */
class ResultsFragment: Fragment() {
    lateinit var resultAdapter:StackOverflowResultsAdapter
    lateinit var resultViewModel:ResultsViewModel
    lateinit var loadingProgressBar: ProgressBar
    lateinit var questionRecyclerView: RecyclerView
    lateinit var previousListState: Parcelable
    val RECYCLER_KEY_STATE = "RECYCLER_STATE"
    lateinit var recyclerViewStateBundle: Bundle
    val PAGE_SIZE = "10"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)
        loadingProgressBar = view.findViewById(R.id.pb_loading_questions)
        questionRecyclerView  = view.findViewById(R.id.rv_stackoverflow_questions)
        questionRecyclerView.layoutManager = LinearLayoutManager(context)

        loadingProgressBar.visibility = View.VISIBLE
        observeResultsFromViewModel(requireArguments().getString("tagg", ""),
            requireArguments().getString("order", ""),
            requireArguments().getString("sort", " "))



        return view
    }
    /**
    *this function observs the LiveData we created in the view model which uses the observer pattern no notify the user if the data has changed
     * @param tagg : the tagg the user is looking for which is retrived for the previous chat screen.
     * @param order :  order the user wishes to see the questions in, descending or ascending
     * @param sort : how the user would like the data to be sorted (activity, votes, ..)
     * @return Nothing
     */
    fun observeResultsFromViewModel( tagg:String,  order:String,  sort:String){
        println("Observing")


        val viewModelFactory = ResultsViewModelFactory(tagg, order, sort, PAGE_SIZE)
        resultViewModel = ViewModelProviders.of(this,viewModelFactory).get(ResultsViewModel::class.java)
        resultViewModel.resultMutableLiveData.observe(viewLifecycleOwner, Observer{

            when(it){
                is Result.Success-> {

                    loadingProgressBar.visibility = View.GONE
                    Toast.makeText(context,  "Questions' Size: "+(it.data as StackExchangeResponse).items.size, Toast.LENGTH_LONG).show()
                    resultAdapter = StackOverflowResultsAdapter(
                        requireContext(),
                        it.data.items
                    )
                    questionRecyclerView.adapter = resultAdapter
                }
                is Result.Error -> {
                    loadingProgressBar.visibility = View.GONE
                    Toast.makeText(context, it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Toast.makeText(context, "ResultFragment is now Visible", Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(context, "ResultFragment Created", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(context, "ResultFragment Started", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(context, "ResultFragment Paused", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(context, "ResultFragment Resumed", Toast.LENGTH_LONG).show()

    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(context, "ResultFragment Stopped", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Toast.makeText(context, "ResultFragment is about to be Destroyed", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(context, "ResultFragment Destroyed", Toast.LENGTH_LONG).show()
    }

    override fun onDetach() {
        super.onDetach()
        Toast.makeText(context, "ResultFragment is removed from Activity", Toast.LENGTH_LONG).show()
    }

}