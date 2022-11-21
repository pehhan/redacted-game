package se.phan.redacted.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import se.phan.redacted.util.Greeting

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLayout()
        }
    }

    @Composable
    private fun MainLayout() {
        ApplicationTheme {
            Background {
                Greeting(Greeting().greeting())
            }
        }
    }
}

@Composable
private fun Background(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        content()
    }
}

@Composable
private fun Greeting(text: String) {
    Text(text = text)
}

@Preview
@Composable
private fun DefaultPreview() {
    ApplicationTheme {
        Greeting("Hello, Android!")
    }
}
