package it

import org.junit.*
import org.junit.Assert.*
import org.openqa.selenium.*
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.*
import java.util.concurrent.TimeUnit

class SeleniumIntegrationTest {

    @Test
    fun testUserInterfaceAvailable() {
        openModuleUrl()
    }

    @After
    fun shutdown() {
        driver.close()
    }

    private fun openModuleUrl() {
        // open the module web page
        driver.get(MODULE_URL)

#if( $atlassianApp == "confluence" )
        // in case of Confluence, login is required
        driver.findElement(By.id("os_username")).sendKeys("admin")
        driver.findElement(By.id("os_password")).sendKeys("admin")
        driver.findElement(By.id("loginButton")).click()
#end

        // loading spinner is there and disappears after a few seconds
        val mainComponent = driver.findElement(By.id("main-component"))
        val loadingSpinnerSelector = By.className("util-align-loading-spinner")
        val loadingSpinner = mainComponent.findElement(loadingSpinnerSelector)
        assertTrue(loadingSpinner.isDisplayed)
        WebDriverWait(driver, 5).until(
            ExpectedConditions.numberOfElementsToBe(loadingSpinnerSelector, 0)
        )

        // after loading spinner disappears, we see the actual main component content
        WebDriverWait(driver, 5).until(
            ExpectedConditions.textToBePresentInElement(
                mainComponent.findElement(By.tagName("div")),
                "Visit us"
            )
        )
    }

    private companion object {
        private const val BASE_URL = "http://localhost:8080"
#if( $atlassianApp == "jira" || $atlassianApp == "jira-insight" )
        private const val MODULE_URL = "$BASE_URL/secure/ModuleAction!default.jspa"
#elseif( $atlassianApp == "confluence" )
        private const val MODULE_URL = "$BASE_URL/plugins/module/module.action"
#end

        private lateinit var driver: WebDriver

        @BeforeClass
        @JvmStatic
        fun configureSelenium() {
            System.setProperty("webdriver.gecko.driver", "target/test-classes/drivers/geckodriver-linux-64bit")
            val capabilities = DesiredCapabilities.firefox()
            driver = FirefoxDriver(capabilities)
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        }

    }

}
