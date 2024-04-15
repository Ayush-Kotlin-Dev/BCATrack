package ggv.ayush.bcatrack.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ggv.ayush.bcatrack.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch (Dispatchers.IO){
            useCases.saveOnBoardingCase(completed)
        }
    }
    private val _onBoardingCompleted =  MutableStateFlow(false)
    val onBoardingCompleted : StateFlow<Boolean> = _onBoardingCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingCompleted.value = useCases.readOnBoardingUseCase().stateIn(viewModelScope).value
        }
    }

}