package com.flower.flower.presentation.ui.flowersList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.flower.flower.R
import com.flower.flower.data.entity.Flower
import com.flower.theme.White
import com.flower.theme.WhiteSmoke

@Composable
fun FlowersListItem(
    modifier: Modifier = Modifier,
    flower: Flower,
    onClick: (flower: Flower) -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(203.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(3.dp))
            .background(
                color = WhiteSmoke
            )
            .clickable {
                onClick(flower)
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = if (flower.profilePicture.contains("http")) rememberImagePainter(flower.profilePicture)
            else rememberImagePainter("https:${flower.profilePicture}"),
            contentDescription = "imageProfileFlower"
        )

        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.gradient_flower),
            contentDescription = "gradientFlower"
        )

        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 4.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.ic_star),
            contentDescription = "iconStarFlower"
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 100.dp, start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = White, fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(R.font.ubuntu_regular)
                    )
                ),
                text = flower.name
            )

            Text(
                modifier = Modifier
                    .padding(top = 9.dp, start = 16.dp, end = 16.dp),
                style = TextStyle(
                    color = White, fontSize = 10.sp,
                    fontFamily = FontFamily(
                        Font(R.font.ubuntu_light_italic)
                    )
                ),
                text = flower.latinName,
                textAlign = TextAlign.Center,
            )

            Text(
                modifier = Modifier
                    .padding(top = 15.dp, start = 16.dp, end = 16.dp, bottom = 23.dp),
                style = TextStyle(
                    color = White, fontSize = 10.sp,
                    fontFamily = FontFamily(
                        Font(R.font.ubuntu_regular)
                    )
                ),
                text = "${flower.sightings} sightings",
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFlowersListItem() {
    FlowersListItem(
        flower = Flower(
            id = 12,
            name = "FlowerName",
            latinName = "LatinName",
            sightings = 4,
            profilePicture = "",
            favorite = false
        )
    )
}
