package com.example.tvremote

enum class TvCommand {
    VOL_UP,
    VOL_DOWN
}

object IrDecoder {
    private fun generatePattern(bytes: IntArray, msbFirst: Boolean): IntArray {
        val list = mutableListOf<Int>()
        list.add(3456)
        list.add(1728)
        for (b in bytes) {
            for (i in 0..7) {
                val bit = if (msbFirst) {
                    (b shr (7 - i)) and 1
                } else {
                    (b shr i) and 1
                }
                list.add(432)
                list.add(if (bit == 1) 1296 else 432)
            }
        }
        list.add(432)
        return list.toIntArray()
    }

    fun getPattern(command: TvCommand): IntArray {
        val patterns = mutableListOf<IntArray>()
        when (command) {
            TvCommand.VOL_UP -> {
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x04, 0x05), false)) // Standard LSB first
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x04, 0x05), true))  // Standard MSB first
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x08, 0x09), false)) // Alt LSB first
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x08, 0x09), true))  // Alt MSB first
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x81, 0x60), true))  // Pronto MSB first
                patterns.add(generatePattern(intArrayOf(0x02, 0x20, 0x80, 0x00, 0x20, 0xa0), false)) // Bit reversed LSB first
            }
            TvCommand.VOL_DOWN -> {
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x04, 0x06), false))
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x04, 0x06), true))
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x08, 0x0A), false))
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x08, 0x0A), true))
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x82, 0x61), true)) 
                patterns.add(generatePattern(intArrayOf(0x02, 0x20, 0x80, 0x00, 0x20, 0x60), false)) 
            }
        }
        
        val blast = mutableListOf<Int>()
        for (pattern in patterns) {
            for (i in 0 until 2) { 
                blast.addAll(pattern.toList())
                blast.add(74767) 
            }
        }
        if (blast.isNotEmpty()) {
            blast.removeAt(blast.size - 1)
        }
        return blast.toIntArray()
    }
}
