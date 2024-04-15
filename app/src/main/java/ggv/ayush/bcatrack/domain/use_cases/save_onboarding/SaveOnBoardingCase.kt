package ggv.ayush.bcatrack.domain.use_cases.save_onboarding

import ggv.ayush.bcatrack.data.repository.Repository


class SaveOnBoardingCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)
    }
}