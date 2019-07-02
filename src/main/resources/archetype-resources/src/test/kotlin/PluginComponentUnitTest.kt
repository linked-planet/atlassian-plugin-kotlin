package ${package}

import org.junit.Test
import ${package}.api.PluginComponent

import org.junit.Assert.assertEquals

class PluginComponentUnitTest {

    @Test
    fun testMyName() {
        assertEquals("${nameHumanReadable}", PluginComponent.name)
    }

}
