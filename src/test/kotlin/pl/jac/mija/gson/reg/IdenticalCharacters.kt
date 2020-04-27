package pl.jac.mija.gson.reg

import org.junit.Test
import java.util.regex.Pattern
import kotlin.test.assertEquals

class IdenticalCharacters {
    @Test
    fun identicalCharacters() {
        // given
        val string = "AAAAAAABFFFZZZBBCCCCCCDDEjacekidzieiiii"
        //when
        val split = string.split(Regex("(?<=(.))(?!\\1)")).maxBy { it.length }
        //then
        assertEquals("AAAAAAA", split)
    }

    @Test
    fun test() {
        // given
        val string = "AAAAAAABFFFZZZBBCCCCCCDDEjacekidzieiiiiiii"
        //when
        val result = string.split(Regex("(?<=(.))(?!\\1)")).groupBy { it.length }.maxBy { it.key }?.value
        //then
        assertEquals(listOf("AAAAAAA", "iiiiiii"), result)
    }

    @Test
    fun test2() {
        // given
        val string = "AAAAAAABFFFZZZBBCCCCCCDDEjacekidzieiiiiiii"
        //when
        val map = string.split(Regex("(?<=(.))(?!\\1)")).groupBy { it.length }
        val result = map.maxBy { it.key }?.value
        //then
        println(map)
    }

    @Test
    fun test3() {
        // given
        val string = "AAAAAAABFFFZZZBBCCCCCCDDEjacekidzieiiiiiii"
        //when
        val findAll = Regex("(.)\\1*").findAll(string).map { it.groupValues[0] }.maxBy { it.length }
        //then
        assertEquals("AAAAAAA", findAll)
    }
    @Test
    fun test4() {
        // given
        val string = "AAAAAAABFFFZZZBBCCCCCCDDEjacekidzieiiiiiii"
        //when
        val split = string.split(Pattern.compile("(.)\\1*"))
//        val map = string.split(Regex("(?<=(.))(?!\\1)")).groupBy { it.length }
//        val result = map.maxBy { it.key }?.value
        //then
        println(split)
    }
}
