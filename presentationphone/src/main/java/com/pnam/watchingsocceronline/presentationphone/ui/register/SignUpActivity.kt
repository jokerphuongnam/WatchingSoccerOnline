package com.pnam.watchingsocceronline.presentationphone.ui.register

import android.app.DatePickerDialog
import android.app.ProgressDialog
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.pnam.watchingsocceronline.data.throwable.EmailInvalid
import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.util.DD_MM_YYYY
import com.pnam.watchingsocceronline.domain.util.toCalendar
import com.pnam.watchingsocceronline.domain.util.toDateTimeString
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivitySignUpBinding
import com.pnam.watchingsocceronline.presentationphone.extension.text
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.net.SocketTimeoutException
import java.util.*

@AndroidEntryPoint
class SignUpActivity: BaseActivity<ActivitySignUpBinding, SignUpViewModel>(R.layout.activity_sign_up) {
    override val viewModel: SignUpViewModel by viewModels()

    private val datePickerCallBack: DatePickerDialog.OnDateSetListener by lazy {
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            binding.apply {
                calendar.timeInMillis.let {
                    user?.birthDay = it
                    birthday.text = it.toDateTimeString(DD_MM_YYYY)
                }
            }
        }
    }

    private val datePicker: DatePickerDialog
        get() {
            val calendar: Calendar = binding.user!!.birthDay.toCalendar
            return DatePickerDialog(
                this,
                datePickerCallBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
            )
        }

    private fun setUpData() {
        binding.apply {
            user = User()
        }
    }

    private fun clearError(vararg textInputLayout: TextInputLayout) {
        textInputLayout.forEach {
            it.error = null
        }
    }

    private fun setUpAction() {
        binding.apply {
            back.setOnClickListener {
                onBackPressed()
            }
            calendarChoose.setOnClickListener {
                datePicker.show()
            }
            register.setOnClickListener {
                binding.apply {
                    clearError(
                        email,
                        password,
                        repeatPassword,
                        firstName,
                        lastName,
                        birthday
                    )
                    user?.gender = User.Gender.values()[genderChoose.selectedItemPosition]
                    var isSuccess = true
                    try {
                        email.error = getString(email.text.emailRegex())
                        isSuccess = false
                    } catch (e: NoErrorException) {
                    }
                    try {
                        password.error = getString(password.text.passwordRegex())
                        isSuccess = false
                    } catch (e: NoErrorException) {
                    }
                    if (!password.text.equals(repeatPassword.text)) {
                        isSuccess = false
                        repeatPassword.error =
                            getString(R.string.repeat_password_need_same_password)
                    }
                    try {
                        firstName.error = getString(firstName.text.nameRegex())
                        isSuccess = false
                    } catch (e: NoErrorException) {
                    }
                    try {
                        lastName.error = getString(lastName.text.nameRegex())
                        isSuccess = false
                    } catch (e: NoErrorException) {
                    }
                    user?.takeIf { it.birthDay >= System.currentTimeMillis() }?.let {
                        isSuccess = false
                        birthday.error = getString(R.string.birthday_need_before_present_time)
                    }
                    if (isSuccess) {
                        user?.let {
                            viewModel.register(it)
                        }
                    }
                }
            }
        }
    }

    private lateinit var show: ProgressDialog

    private fun setUpViewModel() {
        viewModel.apply {
            registerLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                        show = ProgressDialog.show(
                            this@SignUpActivity,
                            "",
                            getString(R.string.loading_dialog)
                        )
                        binding.registerError.isVisible = false
                    }
                    is Resource.Success -> {
                        show.cancel()
                        finish()
                    }
                    is Resource.Error -> {
                        show.cancel()
                        when (it.error) {
                            is EmailInvalid -> {
                                binding.email.error = getString(R.string.email_invalid)
                            }
                            is NotFoundException -> {
                                binding.registerError.apply {
                                    text = getString(R.string.has_error_when_register)
                                    isVisible = true
                                }
                            }
                            is SocketTimeoutException -> {
                                binding.registerError.apply {
                                    text = getString(R.string.timeout)
                                    isVisible = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreateView() {
        setUpData()
        setUpAction()
        setUpViewModel()
    }
}