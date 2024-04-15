package ggv.ayush.bcatrack.domain.use_cases

import ggv.ayush.bcatrack.domain.use_cases.readonboarding.ReadOnBoardingUseCase
import ggv.ayush.bcatrack.domain.use_cases.save_onboarding.SaveOnBoardingCase


data class UseCases(
    val saveOnBoardingCase: SaveOnBoardingCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
)