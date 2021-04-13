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
    private var binding : FragmentNewCitationBinding? = null
    private var tagEditText : EditText? = null
    private var tagsTV : TextView? = null
    private var numTags : Int = 0
    private var viewModel : CitationViewModel = CitationViewModel(application)

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri : Uri)
    }

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewCitationBinding.bind(view)
        tagEditText = binding?.editTextTag
        tagsTV = binding?.tagsTV

        binding?.enterTagButton?.setOnClickListener {
            val numChars : Int? = tagEditText?.text?.count()
            if (numChars != null && numChars < 21) {
                if (tagEditText?.text?.count() != 0 && numTags < 3) {
                    tagsTV?.setText(tagsTV?.text.toString() + "\n" + tagEditText?.text)
                    tagEditText?.setText("")
                    numTags += 1
                } else if (numTags == 3) {
                    tagEditText?.setText("")
                }
            }
        }

        binding?.delTagBtn?.setOnClickListener {
            val tagLen = binding?.editTextTag?.text?.count()
            if (numTags > 0 && tagLen != null && tagLen < 21) {
                val remainingTags = tagsTV?.text?.split("\n")
                var tmpStr: String = "Tags"
                numTags -= 1

                for (i in 1..numTags) {
                    tmpStr += "\n" + remainingTags!![i]
                }

                binding?.tagsTV?.text = tmpStr
            }
        }

        binding?.submitCitationBtn?.setOnClickListener {
            val firstNameLen = binding?.editTextFname?.text?.count()
            val firstName = binding?.editTextFname?.text
            val lastNameLen = binding?.editTextLname?.text?.count()
            val lastName = binding?.editTextLname?.text
            val titleLen = binding?.editTextBookTitle?.text?.count()
            val title = binding?.editTextBookTitle?.text
            val citation = binding?.editTextCitation?.text
            val tags = binding?.tagsTV?.text?.split("\n")

            val pattern = "^[a-zA-Z]+$".toRegex()
            val firstNameValid = pattern.containsMatchIn(firstName.toString())
            val lastNameValid = pattern.containsMatchIn(lastName.toString())

            if (firstNameLen != null && firstNameLen < 21 &&
                lastNameLen != null && lastNameLen < 21 &&
                firstNameValid && lastNameValid && titleLen?.compareTo(51) == -1 && title != null &&
                citation != null && citation.toString() != "" && numTags?.compareTo(0) == 1
                && tags != null) {

                var newCitation : Citation = Citation(firstName.toString(), lastName.toString(),
                        title.toString(), citation.toString())

                var tagObjs = mutableListOf<Tag>()

                for (i in 1..(tags?.count() - 1)) {
                    val newTag : Tag = Tag(tags[i], 0)
                    tagObjs.add(newTag)
                }

                viewModel?.insertCitation(newCitation, tagObjs)

                binding?.editTextFname?.setText("")
                binding?.editTextLname?.setText("")
                binding?.editTextBookTitle?.setText("")
                binding?.editTextCitation?.setText("")
                binding?.tagsTV?.setText(R.string.tags_tv_starting_txt)
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