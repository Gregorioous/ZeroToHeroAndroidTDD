package ru.easycode.zerotoheroandroidtdd

interface Count {
    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {
        init {
            if (step < 1)
                throw IllegalStateException("step should be positive, but was $step")
            if (max < 1)
                throw IllegalStateException("max should be positive, but was $max")
            if (max < min)
                throw IllegalStateException("max should be more than min")
            if (max < step)
                throw IllegalStateException("max should be more than step")
        }

        override fun initial(number: String): UiState {
            val digits = number.toInt()
            return when (digits) {
                min -> UiState.Min(number)
                max -> UiState.Max(number)
                else -> UiState.Base(number)
            }
        }

        override fun increment(number: String): UiState {
            val result = number.toInt() + step
            return initial(result.toString())
        }

        override fun decrement(number: String): UiState {
            val result = number.toInt() - step
            return initial(result.toString())
        }

    }

    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

}
