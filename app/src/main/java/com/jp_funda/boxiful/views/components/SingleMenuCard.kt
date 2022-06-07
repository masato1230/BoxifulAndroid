package com.jp_funda.boxiful.views.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.SingleMenu
import com.jp_funda.boxiful.ui.theme.*
import kotlin.math.roundToInt

@Composable
fun SingleMenuCard(menu: SingleMenu, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .heightIn(min = 280.dp)
            .width(250.dp)
            .clickable { onClick() },
        backgroundColor = Color.White,
    ) {
        Column {
            // Menu Thumbnail
            Image(
                painter = painterResource(id = menu.getThumbnailRes()),
                contentDescription = stringResource(id = R.string.menu),
                modifier = Modifier
                    .height(120.dp)
                    .padding(10.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Yellow500)
                    .fillMaxWidth(),
            )

            // Menu Title
            Text(
                text = stringResource(id = menu.titleRes),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.button,
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.height(5.dp))

            // Menu Description
            Text(
                text = stringResource(id = menu.descriptionRes),
                modifier = Modifier.padding(horizontal = 10.dp),
                style = MaterialTheme.typography.caption,
                fontFamily = FontFamily.Serif,
            )

            Spacer(modifier = Modifier
                .weight(1f)
                .heightIn(10.dp))

            // Approximate calorie consumption
            val approximateCalorieText =
                stringResource(id = R.string.menu_approximate_calorie_consumption) +
                        " " +
                        stringResource(R.string.unit_kcal, menu.calorieConsumption)
            IconLabel(
                iconRes = R.drawable.ic_fire,
                label = approximateCalorieText,
                iconColor = Red500,
                backgroundColor = Red100,
            )

            Spacer(modifier = Modifier.height(5.dp))

            // Duration in minutes
            IconLabel(
                iconRes = R.drawable.ic_timer,
                label = stringResource(
                    id = R.string.menu_duration_in_minutes,
                    menu.durationInMinutes.roundToInt(),
                ),
                iconColor = Blue500,
                backgroundColor = Blue100,
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun IconLabel(
    @DrawableRes iconRes: Int,
    label: String,
    iconColor: Color,
    backgroundColor: Color,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(1000.dp))
            .background(backgroundColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = stringResource(id = R.string.desc_icon),
            tint = iconColor,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = label,
            color = Color.DarkGray,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}