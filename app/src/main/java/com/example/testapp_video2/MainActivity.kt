package com.example.testapp_video2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.testapp_video2.navigation.MovieNavigation
import com.example.testapp_video2.ui.theme.TestApp_Video2Theme

class MainActivity : ComponentActivity() {
    /*
}
    override fun onStop() {
        super.onStop()
        Log.i("Main Activity", "onStop Cycle")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Main Activity", "onStart Cycle")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Main Activity", "onResume Cycle")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Main Activity", "onResume Cycle")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Main Activity", "onDestroy Cycle")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Main Activity", "onRestart Cycle")
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.i("Main Activity", "onCreate Cycle")


        //val favoritesViewModel : FavoritesViewModel by viewModels()
        //favoritesViewModel.favoriteMovies

        setContent {
            //val vm2 : FavoritesViewModel = viewModel()
            //vm2.favoriteMovies

            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp( content : @Composable () -> Unit ) {
    TestApp_Video2Theme {
       content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation()
    }
}