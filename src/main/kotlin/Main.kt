import engine.Tower
import enumClasses.TowerState

fun main() {
    val MAX_HEIGHT = 60.0
    val MAX_WEIGHT = 800.0

    try {
        val tower = Tower(MAX_HEIGHT, MAX_WEIGHT, 2.0, 15.0, 16)

        println("---- Ride Report -----")

        tower.isLocked = true

        println(
            "Passengers loaded, seatbelts locked, coaster ready to start.....\n"+
            "Is it safe to start? ${if(tower.safetyCheck(0.0)) "Yes" else "No"}"
        )

        Thread.sleep(5_000)

        for(i in 1..10) {
            var currentSeconds = 1
            var dropCountdown:Int = tower.waitingTime + 1

            do {
                val previousState = tower.currentState
                tower.updateStatus(currentSeconds)

                if(previousState != tower.currentState) {
                    when(previousState) {
                        TowerState.LOADING -> println("---- Phase $i | Ascending Phase -----\n")
                        TowerState.ASCENT -> println("---- Phase $i | Waiting Phase ----\n")
                        TowerState.WAITING -> println("---- Phase $i | Dropping Phase ----\n")
                        TowerState.DROP -> if(!tower.isLocked)  println("\n\n---- Phase ${i + 1} | Loading Phase ---") else  println("\n\n---- Phase ${i + 1} | Ascending Phase ---")
                    }
                }

                if(tower.currentState == TowerState.WAITING) {
                    dropCountdown -= 1

                    println("The tower is currently waiting $dropCountdown to drop")
                } else {
                    println("The tower is currently at ${tower.currentHeight} meters")
                }

                Thread.sleep(1_000)
                currentSeconds++
            } while(tower.currentHeight > 0)
        }

        tower.isLocked = false
        println("Test sequences finished, seatbelts unlocked, coaster in loading mode awaiting restart.")
    } catch(error:IllegalArgumentException) {
        println("One of the input values wasn't accepted by the object:\n${error.message}")
    } catch(error:Exception) {
        println("There has been a general error while running the program:\n${error.message}")
    }
}