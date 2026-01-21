package engine

import enumClasses.TowerState

class Tower(val maxHeight:Double, val maxWeight:Double, ascentSpeedValue:Double, descentSpeedValue:Double, waitingTimeValue:Int) {
    init {
        require(maxHeight > 0.0){"The maximum height of the tower must be higher than 0"}
        require(maxWeight > 0.0){"The maximum weight of the tower must be higher than 0"}
        require(ascentSpeedValue in 0.0..descentSpeedValue){"The ascent speed value must be higher than 0 and lower than the descent speed value"}
        require(descentSpeedValue > 0.0){"The descent speed value must be higher than 0"}
        require(waitingTimeValue > 0){"The coaster must at least wait more than 0 seconds once up the tower before dropping"}
    }

    var ascentSpeed:Double = ascentSpeedValue
        set(value) {
            require(value in 0.0..descentSpeed){"The ascent speed value must be higher than 0 and lower than the descent speed value"}

            field = value
        }

    var descentSpeed:Double = descentSpeedValue
        set(value) {
            require(value > 0.0){"The descent speed value must be higher than 0"}

            field = value
        }

    var waitingTime:Int = waitingTimeValue
        set(value) {
            require(value > 0){"The coaster must at least wait more than 0 seconds once up the tower before dropping"}

            field = value
        }

    var currentHeight:Double = 0.0
        private set(value) {
            require(value in 0.0..maxHeight){"The current height from ground value must be within 0 and ${maxHeight} (max height for this tower) meters"}

            field = value
        }

    var currentState: TowerState = TowerState.LOADING
        private set

    var isLocked:Boolean = false

    fun updateStatus(secondsFromStart:Int) {
        require(secondsFromStart >= 0){"You must provide a number of seconds equal or higher than 0"}

        val ascentTime:Int = (maxHeight/ascentSpeed).toInt()
        val dropTime:Int = (maxHeight/descentSpeed).toInt()
        val totalCycleTime:Int = ascentTime + dropTime + waitingTime

        if(isLocked) {
            val currentCycle = secondsFromStart % totalCycleTime

            when(currentCycle) {
                in 0..<ascentTime -> {
                    currentState = TowerState.ASCENT
                    currentHeight = ascentSpeed * currentCycle
                }

                in ascentTime..<ascentTime + waitingTime -> {
                    currentState = TowerState.WAITING
                    currentHeight = maxHeight
                }

                in ascentTime + waitingTime..<ascentTime + waitingTime + dropTime -> {
                    currentState = TowerState.DROP
                    currentHeight = maxHeight - (descentSpeed * (currentCycle - (ascentTime + waitingTime)))
                }

                else -> {
                    currentState = TowerState.LOADING
                    currentHeight = 0.0
                    isLocked = false
                }
            }
        } else {
            currentState = TowerState.LOADING
        }
    }

    fun safetyCheck(coasterWeight:Double):Boolean {
        require(coasterWeight >= 0.0){"It is impossible that your coaster weights less than 0 kilograms!"}

        return coasterWeight <= maxWeight
    }
}