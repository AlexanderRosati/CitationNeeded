package com.ebookfrenzy.citationneeded

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchRecyclerAdapter(quotes : List<Citation>, private var viewModel : CitationViewModel) :
        RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {
    var citations : MutableList<Citation> = quotes.toMutableList() //list of citations

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var citationTV : TextView //citation text view
        var authorNameTV : TextView //author name text view
        var bookTitleTV : TextView //book title text view
        var delBtn : Button //delete button
        var citationID : Long = 0 //citationID text view
        var indx : Int = 0 //index in 'citations' list

        init {
            //get views
            citationTV = itemView.findViewById(R.id.citationTextSearchByTag)
            authorNameTV = itemView.findViewById(R.id.authorNameSearchByTag)
            bookTitleTV = itemView.findViewById(R.id.bookTitleSearchByTag)
            delBtn = itemView.findViewById(R.id.deleteBtnSearchByTag)

            //set text for delete button
            delBtn.text = "Delete Citation"

            //when click delete button
            delBtn.setOnClickListener {
                //remove from 'citations' list, notify recycler, and delete one record from
                //CITATION table and up to three records from TAG table
                citations.removeAt(indx)
                notifyDataSetChanged()
                viewModel.deleteCitation(citationID)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup : ViewGroup, i : Int) : ViewHolder {
        //create and return a view holder
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder : ViewHolder, i : Int) {
        //put data into views
        viewHolder.citationTV.text = "\"" + citations[i].citation + "\""
        viewHolder.authorNameTV.text =  citations[i].fname + " " + citations!![i].lname
        viewHolder.bookTitleTV.text = citations[i].bookName

        //save citationID and index
        viewHolder.citationID = citations[i].citationID
        viewHolder.indx = i
    }

    override fun getItemCount() : Int {
        return citations.size
    }

    fun updateCitations(newCitations : List<Citation>) {
        //update 'citations' list with new list
        citations = newCitations.toMutableList()
        notifyDataSetChanged()
    }
}