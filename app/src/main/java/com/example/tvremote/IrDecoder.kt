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
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x04, 0x05), true))
            }
            TvCommand.VOL_DOWN -> {
                patterns.add(generatePattern(intArrayOf(0x40, 0x04, 0x01, 0x00, 0x84, 0x85), true))
            }
        }
        
        val blast = mutableListOf<Int>()
        for (pattern in patterns) {
            for (i in 0 until 3) { // ส่งซ้ำ 3 รอบตามมาตรฐาน Panasonic เพื่อความแม่นยำ
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
