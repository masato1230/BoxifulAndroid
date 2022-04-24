package com.jp_funda.boxful.views.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.jp_funda.boxful.R
import com.jp_funda.boxful.models.SingleMenu
import com.jp_funda.boxful.ui.theme.*

@Composable
fun SingleMenuCard(menu: SingleMenu, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .heightIn(min = 300.dp)
            .width(250.dp)
    ) {
        Column {
            // Menu Thumbnail
            Image(
                painter = painterResource(id = menu.getThumbnail()),
                contentDescription = stringResource(id = R.string.menu),
                modifier = Modifier
                    .background(Yellow500)
                    .height(120.dp)
                    .fillMaxWidth(),
            )

            // Menu Title
            Text(
                text = stringResource(id = menu.titleRes),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.button,
                fontWeight = FontWeight.Bold,
            )

            // Menu Description
            Text(
                text = stringResource(id = menu.descriptionRes),
                modifier = Modifier.padding(horizontal = 5.dp),
                style = MaterialTheme.typography.body2,
                color = Color.LightGray,
                fontFamily = FontFamily.Serif,
            )

            Spacer(modifier = Modifier.weight(1f))

            // Approximate calorie consumption
            val approximateCalorieText =
                stringResource(id = R.string.menu_approximate_calorie_consumption) +
                        " " +
                        menu.calorieConsumption +
                        stringResource(R.string.kcal)
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
                    menu.durationInMinutes,
                ),
                iconColor = Blue500,
                backgroundColor = Blue100,
            )

            Spacer(modifier = Modifier.height(5.dp))
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