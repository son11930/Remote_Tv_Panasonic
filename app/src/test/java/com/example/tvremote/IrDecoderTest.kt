package com.example.tvremote

import org.junit.Assert.assertArrayEquals
import org.junit.Test

class IrDecoderTest {

    @Test
    fun testVolUpPattern() {
        val decoder = IrDecoder()
        val expected = intArrayOf(3456, 1728, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432)
        assertArrayEquals(expected, decoder.getPattern("VOL_UP"))
    }

    @Test
    fun testVolDownPattern() {
        val decoder = IrDecoder()
        val expected = intArrayOf(3456, 1728, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432)
        assertArrayEquals(expected, decoder.getPattern("VOL_DOWN"))
    }
    
    @Test
    fun testUnknownPattern() {
        val decoder = IrDecoder()
        val expected = intArrayOf()
        assertArrayEquals(expected, decoder.getPattern("UNKNOWN"))
    }
}
