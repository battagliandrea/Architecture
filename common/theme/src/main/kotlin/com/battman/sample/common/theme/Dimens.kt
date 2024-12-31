package com.battman.sample.common.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val spacing3XS: Dp,
    val spacing2XS: Dp,
    val spacingXS: Dp,
    val spacingS: Dp,
    val spacingM: Dp,
    val spacingL: Dp,
    val spacingXL: Dp,
    val spacingXXL: Dp,
    val iconS: Dp,
    val iconM: Dp,
    val iconL: Dp,
    val roundCornerS: Dp,
    val roundCornerM: Dp,
    val roundCornerL: Dp,
    val thicknessS: Dp,
    val thicknessM: Dp,
    val thicknessL: Dp,
    val elevationS: Dp,
    val elevationM: Dp,
    val elevationL: Dp,
    val elevationNone: Dp = 0.dp,
    val toolbarHeight: Dp = 52.dp,
)

internal val defaultDimensions = Dimensions(
    spacing3XS = 2.dp,
    spacing2XS = 4.dp,
    spacingXS = 8.dp,
    spacingS = 12.dp,
    spacingM = 16.dp,
    spacingL = 24.dp,
    spacingXL = 32.dp,
    spacingXXL = 48.dp,
    iconS = 24.dp,
    iconM = 36.dp,
    iconL = 72.dp,
    roundCornerS = 8.dp,
    roundCornerM = 18.dp,
    roundCornerL = 24.dp,
    thicknessS = 1.dp,
    thicknessM = 2.dp,
    thicknessL = 4.dp,
    elevationS = 2.dp,
    elevationM = 4.dp,
    elevationL = 8.dp
)

internal val LocalDimensions = staticCompositionLocalOf {
    defaultDimensions
}
