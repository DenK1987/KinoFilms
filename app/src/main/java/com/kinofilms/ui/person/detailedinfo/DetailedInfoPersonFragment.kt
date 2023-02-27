package com.kinofilms.ui.person.detailedinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kinofilms.R
import com.kinofilms.databinding.FragmentDetailedInfoPersonBinding
import com.kinofilms.ui.person.PersonViewModel
import com.kinofilms.utils.getAge
import com.kinofilms.utils.string
import com.kinofilms.utils.transformDateInString
import com.kinofilms.utils.transformGrowthPerson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedInfoPersonFragment : Fragment() {

    private lateinit var binding: FragmentDetailedInfoPersonBinding

    private val viewModel: PersonViewModel by viewModels()

    private val args: DetailedInfoPersonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailedInfoPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()

        viewModel.person.observe(viewLifecycleOwner) { person ->
            binding.apply {
                professions.text = person.profession.string()
                if (person.growth != 0)
                    growthPerson.text = transformGrowthPerson(person.growth)
                else growthPerson.text = getString(R.string.dash)
                if (person.age != 0) {
                    agePerson.text =
                        resources.getQuantityString(
                            R.plurals.plurals_age,
                            getAge(person.birthday),
                            getAge(person.birthday)
                        )
                } else agePerson.text = getString(R.string.dash)
//                birthdayPerson.text = "${transformDateInString(person.birthday)} ${'•'} ${resources.getQuantityString(
//                    R.plurals.plurals_age,
//                    getAge(person.birthday),
//                    getAge(person.birthday)
//                )}"
                if (person.birthday.isNotEmpty())
                    birthdayPerson.text = transformDateInString(person.birthday)
                else birthdayPerson.text = getString(R.string.dash)
                if (person.birthPlace.isNotEmpty())
                    birthPlacePerson.text = person.birthPlace.string()
                else birthPlacePerson.text = getString(R.string.dash)
                if (person.death.isNotEmpty())
                    deathPerson.text = transformDateInString(person.death)
                else {
                    deathPerson.isVisible = false
                    death.isVisible = false
                }
                if (person.deathPlace.isNotEmpty())
                    deathPlacePerson.text = person.deathPlace.string()
                else {
                    deathPlace.isVisible = false
                    deathPlacePerson.isVisible = false
                }
            }
        }
        viewModel.getInfoPerson(args.idPerson.toInt())
    }

    private fun initToolbar() {
        with(binding.toolbarCustom) {
            toolbarButtonBack.visibility = View.VISIBLE
            toolbarButtonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarTitle.text = args.namePerson
        }
    }
}