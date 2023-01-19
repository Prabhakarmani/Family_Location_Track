package prabhakarn.manish.familylocation

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import prabhakarn.manish.familylocation.Fragment.GuardFragment
import prabhakarn.manish.familylocation.Fragment.HomeFragment
import prabhakarn.manish.familylocation.Fragment.MapsFragment

class MainActivity : AppCompatActivity()
{
    private val permissions= arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    val permissioncode=23
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askforpermission()

        val bottombar = findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottombar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_guard -> {
                    inflateFragment(GuardFragment.newInstance())
                }
                R.id.nav_home -> {
                    inflateFragment(HomeFragment.newInstance())
                }

                R.id.nav_map -> {
                    inflateFragment(MapsFragment.newInstance())
                }
            }
            true
        }
        bottombar.selectedItemId=R.id.nav_home

    }

    private fun askforpermission()
    {
        ActivityCompat.requestPermissions(this,permissions,permissioncode)

    }

    private fun inflateFragment(newInstance: Fragment)
    {
        val transection = supportFragmentManager.beginTransaction()
        transection.replace(R.id.container,newInstance)
        transection.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode==permissioncode)
        {
            if(allPermissionGranted())
            {

            }
        }
    }

    private fun allPermissionGranted(): Boolean
    {
        for(item in permissions){
            if(ContextCompat.checkSelfPermission(this,item)!=PackageManager.PERMISSION_GRANTED)
            {
                return false
            }
        }
        return true
    }
}