package org.adaschool.taskplanner.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.adaschool.taskplanner.storage.Storage
import org.adaschool.taskplanner.ui.task.activity.TasksActivity
import javax.inject.Inject

/**
 * @author Santiago Carrillo
 * 25/10/21.
 */

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    @Inject
    lateinit var storage: Storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (storage.isUserAuth()) {
            launchMainActivity()
        } else {
            launchLoginActivity()
        }
        finish()
    }

    private fun launchLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun launchMainActivity() {
        val intent = Intent(this, TasksActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}