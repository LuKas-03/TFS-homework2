package tfs.homeworks.homework2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // подписываем активити на прослушивание изменений backStack'a
        supportFragmentManager.addOnBackStackChangedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item = menu?.findItem(R.id.action_delete)
        if (item != null) {
            // включение активности кнопки "удалить" если документы есть
            if (supportFragmentManager.backStackEntryCount > 0) {
                item.isEnabled = true
            }
            return true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_add -> {
                onMenuAddClicked()
                true
            }
            R.id.action_delete -> {
                onMenuDeleteClicked()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackStackChanged() {
        // обновление меню при добавлении первого или удалении последнего фрагмента
        if (supportFragmentManager.backStackEntryCount < 2) {
            invalidateOptionsMenu()
        }
    }

    private fun onMenuAddClicked() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                DocumentFragment.newInstance(supportFragmentManager.backStackEntryCount + 1)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun onMenuDeleteClicked() {
        supportFragmentManager.popBackStack()
    }
}
