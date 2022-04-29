package com.jp_funda.boxiful.views.training.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.Instruction
import com.jp_funda.boxiful.ui.theme.BlackAlpha50
import com.jp_funda.boxiful.ui.theme.Blue500
import com.jp_funda.boxiful.ui.theme.Green500
import com.jp_funda.boxiful.ui.theme.Purple500

@Composable
fun BottomInstructionOverlay(instruction: Instruction) {
    val iconDegree = when (instruction) {
        Instruction.LeftHandLeftPunch,
        Instruction.RightHandLeftPunch,
        Instruction.LeftFootLeftKick,
        Instruction.RightFootLeftKick -> 0f
        Instruction.LeftHandRightPunch,
        Instruction.RightHandRightPunch,
        Instruction.LeftFootRightKick,
        Instruction.RightFootRightKick -> 180f
    }
    val iconColor = when (instruction) {
        Instruction.LeftHandLeftPunch,
        Instruction.LeftHandRightPunch -> Blue500
        Instruction.LeftFootLeftKick,
        Instruction.LeftFootRightKick -> Green500
        Instruction.RightHandLeftPunch,
        Instruction.RightHandRightPunch -> Color.Red
        Instruction.RightFootLeftKick,
        Instruction.RightFootRightKick -> Purple500
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackAlpha50)
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = instruction.titleRes),
            fontSize = MaterialTheme.typography.h4.fontSize * 1.2,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(10.dp))

        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = stringResource(id = R.string.desc_left),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(iconColor)
                .rotate(iconDegree)
        )
    }
}