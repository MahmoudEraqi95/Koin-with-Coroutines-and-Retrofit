package com.eraqi.siatask.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eraqi.siatask.R

/**
 *this is the main class which acts as a container for our navigation graph
 * the navigation graph handles the navigation between all of our screens
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpActionBar()
    }
    /**
     *this function sets up the action bar for the activity and makes the back button visible and clickable
     * @return Nothing.
     */
    fun setUpActionBar(){

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

    }
    /**
     *we handle what happens the back button is clicked, super.onNackPressed simply means doing the default action in appCompat
     * which is going back to previous screen , but it won't do that for splash as we have called "finish()"
     * which calls onDestroy of activity
     * @return Nothing.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            super.onBackPressed()

        }
        return super.onOptionsItemSelected(item)
    }
}
