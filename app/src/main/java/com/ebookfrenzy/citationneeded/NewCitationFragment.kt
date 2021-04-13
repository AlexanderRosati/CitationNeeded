package com.ebookfrenzy.citationneeded

import android.app.Application
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.ebookfrenzy.citationneeded.databinding.FragmentNewCitationBinding

class NewCitationFragment(application: Application) : Fragment() {
    private var binding : FragmentNewCitationBinding? = null //binding
    private var tagEditText : EditText? = null //edit text for tags
    private var tagsTV : TextView? = null //text view for tags
    private var numTags : Int = 0 //number of tags
    private var viewModel : CitationViewModel = CitationViewModel(application) //view model

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri : Uri)
    }

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get binding
        binding = FragmentNewCitationBinding.bind(view)

        //getting text view and edit texts for tags
        tagEditText = binding?.editTextTag
        tagsTV = binding?.tagsTV

        //click enter tag button
        binding?.enterTagButton?.setOnClickListener {
            //num characters entered
            val numChars : Int? = tagEditText?.text?.count()

            //if num characters less than 21
            if (numChars != null && numChars < 21) {

                //if not zero characters and not already three tags
                if (tagEditText?.text?.count() != 0 && numTags < 3) {
                    tagsTV?.setText(tagsTV?.text.toString() + "\n" + tagEditText?.text)
                    tagEditText?.setText("")
                    numTags += 1

                //clear is already three tags
                } else if (numTags == 3) {
                    tagEditText?.setText("")
                }
            }
        }

        //when delete tag button is clicked
        binding?.delTagBtn?.setOnClickListener {
            // if at least one tag
            if (numTags > 0) {
                //get the tags
                val remainingTags = tagsTV?.text?.split("\n")

                //build new string
                var tmpStr: String = "Tags"

                //one less tag
                numTags -= 1

                //iterate through tags and build new string
                for (i in 1..numTags) {
                    tmpStr += "\n" + remainingTags!![i]
                }

                //assign to text view
                binding?.tagsTV?.text = tmpStr
            }
        }

        //when submit button is clicked
        binding?.submitCitationBtn?.setOnClickListener {
            val firstNameLen = binding?.editTextFname?.text?.count() //get first name length
            val firstName = binding?.editTextFname?.text //get first name
            val lastNameLen = binding?.editTextLname?.text?.count() //get last name length
            val lastName = binding?.editTextLname?.text //get last name
            val titleLen = binding?.editTextBookTitle?.text?.count() //get title length
            val title = binding?.editTextBookTitle?.text //get title
            val citation = binding?.editTextCitation?.text //get citation
            val tags = binding?.tagsTV?.text?.split("\n") //get tags

            //validate first and last name with regular expression
            val pattern = "^[a-zA-Z]+$".toRegex()
            val firstNameValid = pattern.containsMatchIn(firstName.toString())
            val lastNameValid = pattern.containsMatchIn(lastName.toString())

            //make sure all fields are valid and continue
            if (firstNameLen != null && firstNameLen < 21 &&
                lastNameLen != null && lastNameLen < 21 &&
                firstNameValid && lastNameValid && titleLen?.compareTo(51) == -1 && title != null &&
                citation != null && citation.toString() != "" && numTags?.compareTo(0) == 1
                && tags != null) {

                //citation to enter
                var newCitation : Citation = Citation(firstName.toString(), lastName.toString(),
                        title.toString(), citation.toString())

                //mutable list of tab objects
                var tagObjs = mutableListOf<Tag>()

                //iterate through tags and create tag objects
                for (i in 1..(tags?.count() - 1)) {
                    val newTag : Tag = Tag(tags[i], 0)
                    tagObjs.add(newTag)
                }

                //insert record into CITATION table and one record for each tag into TAG table
                viewModel?.insertCitation(newCitation, tagObjs)

                //clear all fields
                binding?.editTextFname?.setText("")
                binding?.editTextLname?.setText("")
                binding?.editTextBookTitle?.setText("")
                binding?.editTextCitation?.setText("")
                binding?.tagsTV?.setText(R.string.tags_tv_starting_txt)

                //make number of tags 0
                numTags = 0
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_citation, container, false)
    }
}