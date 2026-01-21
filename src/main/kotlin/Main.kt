import engine.Tower

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

        println("---- Ascent Phase ----")

        var currentSeconds = 0
        while(tower.currentHeight < tower.maxHeight) {
            tower.updateStatus(currentSeconds)
            println("The tower is currently at ${tower.currentHeight} meters")

            currentSeconds++
        }

        println(
            "\n"+
            "The tower has reached the top, preparing to drop in ${tower.waitingTime} seconds...."+
            "\n"
        )
        Thread.sleep(5_000)

        for(i in 0..9) {
            println("---- Descent Phase $i -----")

            var dropSeconds = (tower.maxHeight/tower.ascentSpeed).toInt() + tower.waitingTime
            do {
                tower.updateStatus(dropSeconds)
                println("The tower is currently at ${tower.currentHeight} meters")

                dropSeconds++
            } while(tower.currentHeight > 0)

            println("\n\n")
        }

        tower.isLocked = false
        println("Test sequences finished, seatbelts unlocked, coaster in loading mode awaiting restart.")
    } catch(error:IllegalArgumentException) {
        println("One of the input values wasn't accepted by the object:\n${error.message}")
    } catch(error:Exception) {
        println("There has been a general error while running the program:\n${error.message}")
    }
}