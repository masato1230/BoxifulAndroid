package com.jp_funda.boxiful.views.result.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.ResultStats
import com.jp_funda.boxiful.ui.theme.Blue500
import com.jp_funda.boxiful.ui.theme.Pink500
import com.jp_funda.boxiful.ui.theme.Red500

@Composable
fun ResultDetailSection(resultStats: ResultStats) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        contentColor = Color.Black,
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = stringResource(id = R.string.result_detail),
                color = Color.Black,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.ExtraBold,
            )
            // TODO Graph
            // Great count
            Text(
                text = "${stringResource(id = R.string.great)} ${resultStats.greatCount} ${
                    stringResource(id = R.string.result_times)
                }",
                color = Pink500,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 5.dp),
            )
            // Good count
            Text(
                text = "${stringResource(id = R.string.good)} ${resultStats.goodCount} ${
                    stringResource(id = R.string.result_times)
                }",
                color = Blue500,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 20.dp, bottom = 5.dp)
            )
            // Miss count
            Text(
                text = "${stringResource(id = R.string.miss)} ${resultStats.missCount} ${
                    stringResource(id = R.string.result_times)
                }",
                color = Color.Gray,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 20.dp)
            )

            Divider(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .clip(RoundedCornerShape(1000.dp)),
                thickness = 3.dp,
                color = Color.Gray
            )

            // Calorie consumption
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fire),
                    contentDescription = stringResource(id = R.string.desc_icon),
                    tint = Red500,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = stringResource(id = R.string.calories_burned),
                    color = Color.DarkGray,
                    fontWeight = FontWeight.ExtraBold,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.unit_kcal, resultStats.caloriesBurned),
                    modifier = Modifier.padding(end = 20.dp),
                )
            }
        }
    }
}