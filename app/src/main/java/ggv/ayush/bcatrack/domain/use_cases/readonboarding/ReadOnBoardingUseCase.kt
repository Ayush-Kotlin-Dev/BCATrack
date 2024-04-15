package ggv.ayush.bcatrack.domain.use_cases.readonboarding

import ggv.ayush.bcatrack.data.repository.Repository

class ReadOnBoardingUseCase (
     private val repository: Repository
) {
    operator fun invoke() = repository.readOnBoardingState()
}