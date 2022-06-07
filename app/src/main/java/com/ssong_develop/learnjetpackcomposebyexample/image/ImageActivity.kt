package com.ssong_develop.learnjetpackcomposebyexample.image

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.launch

/**
 * side-effect 란 Compose function 외부에서 발생하는 앱 상태의 변화를 의미한다.
 *
 * Composable function은 side-effect 에 free 홰야한다
 *
 * Compose를 변화시키기 위해서는 변경된 param을 통해서 재호출하는 형태만 존재해야 하며, compose 함수 내부에서 외부에 있는
 * 변수나, 동작에 영향을 주도록 구성되어서는 안된다.
 * ) compose의 lifecycel에 의해서 recompose가 수시로 발생할 수 있고, 여러 thread에서 호출될 수 있기 때문이다.
 *
 * Compose 내부의 동작으로 인해 외부의 상태가 바뀌어야 하는 경우가 발생할 수 있기 떄문에 이때는 Compose의 lifecycle을 인식하고,
 * 이에 맞게 동작하는 형태로 구현되어야 한다.
 *
 * composition이 완료될 떄 side-effect를 처리하는 composable function을 지원합니다.
 * effect라는 단어로 정의하며, effect란 UI를 방출하지 않는 composable function입니다.
 *
 */

/**
 *
 * LaunchedEffect
 *
 * suspend function을 Compose 내에서 호출하기 위해 사용
 *
 * 실행시점 : Composition enter
 *
 * 종료시점 : Composition leave
 *
 * 재실행 Trigger 조건 : block param의 변경
 *
 * LaunchedEffect로 생성된 coroutine scope는 기본적으로 해당 Composable의 생성 주기를 따른다.
 *
 * Compose 생성 시 launch되고, Compose가 화면에서 사라지면 같이 cancel된다.
 *
 * coroutine의 structured concurrency가 Composable의 lifecycle에 같이 묶인다.
 *
 */

/**
 *
 * rememberCoroutineScope
 *
 * LaunchedScope은 composable function으로 composable function 안에서만 호출할 수 있다.
 *
 * 그외 부분에서 coroutine scope를 얻기 위해선 rememberCoroutineScope을 사용한다.
 *
 * 실행시점 : Composition enter
 *
 * 종료시점 : Composition leave
 *
 * 재실행 trigger 조건 : 없음
 */

/**
 *
 * rememberUpdatedState
 *
 * LaunchedEffect의 내부 block에서 접근해야 하는 변수는 Key로 넘겨져야한다.
 *
 * param에 변경이 발생하면 해당 coroutine을 취소하고 재시작한다.
 *
 * LaunchedEffect의 block내부에서 접근은 해야 하나
 * 변경되더라도 LaunchedEffect를 재시작시키지 않도록 하기 위해 rememberUpdatedState로 WRAPPING한다.
 *
 * rememberUpdatedState는 해당 값의 reference를 만들며, 실제의 값을 capture하고 수정이 되면 수정된 값이 capture된다.
 *
 * 오래 기다려야 하는 작업을 유지, 무거운 연산, recomposition에 의해 restart가 되지 않도록 할 때 유용용
 */

/**
 *
 * DisposableEffect
 *
 * side-effect를 처리 도중 재시작되거나 or param이 변경되거나, compose가 leave 상태가 될 경우 특정 resource를 해지해야할 때 사용한다.
 *
 * 실행시점 : Composition enter
 *
 * 종료시점 : Composition leave
 *
 * 재실행 trigger 조건 : block param의 변경
 *
 * LaunchedEffect와 동일하나, 재시작으로 인한 취소, Compose leave로 인한 종료시 onDispose() 구문이 항상 호출
 *
 * Callback의 형태로 구현을 진행해야하는 경우 유용용
 */

/**
 * produceState
 *
 * return 형으로 State<T>를 반환할 수 있다.
 */

/**
 *
 * derivedStateOf
 *
 * 여러 개의 상태의 변경을 trigger 삼아 자신의 상태를 변경
 *
 * MediatorLiveData , flow combine과 비슷하다 생각
 */

/**
 *
 * snapshotFlow
 *
 * state를 flow로 변환하기 위해 사용
 *
 * collectAsState()는 연산자를 사용못하는데 얘를 사용하면 연산자 사용이 가능
 */

class ImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkImageComponentCoil(url = "https://picsum.photos/500/300?random=3")
        }
    }
}

@Composable
fun NetworkImageComponentCoil(
    url: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(url)
        .build()
    val imagePainter = rememberAsyncImagePainter(
        remember(url) { request }
    )
    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }

    // Coil을 활용해 image를 bitmap 형태로 가져올 수 있습니다.
    LaunchedEffect(url) {
        launch {
            val result = imagePainter.imageLoader.execute(imagePainter.request).drawable
            val bitmap = result?.toBitmap()?.asImageBitmap()
            drawable = result
            image = bitmap
        }
    }

    if (image != null) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image is a pre-defined composable that lays out and draws a given [ImageBitmap].
            Image(
                bitmap = image!!,
                contentDescription = null
            )
        }
    }
}