package engine

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Test

import enumClasses.TowerState

class TowerTest {

 /**** Constructor ****/

 @Test
 fun constructor_maxHeightLowerThan0_IllegalArgumentException() {
  assertThrows<IllegalArgumentException> { Tower(-1.0, 800.0, 2.0, 15.0, 5) }
 }

 @Test
 fun constructor_maxHeightEqualTo0_IllegalArgumentException() {
  assertThrows<IllegalArgumentException> { Tower(0.0, 800.0, 2.0, 15.0, 5) }
 }

 @Test
 fun constructor_maxWeightLowerThan0_IllegalArgumentException() {
  assertThrows<IllegalArgumentException> { Tower(60.0, -1.0, 2.0, 15.0, 5) }
 }

 @Test
 fun constructor_maxWeightEqualTo0_IllegalArgumentException() {
  assertThrows<IllegalArgumentException> { Tower(60.0, 0.0, 2.0, 15.0, 5) }
 }

 @Test
 fun constructor_ascentSpeedLowerThan0_IllegalArgumentException() {
  assertThrows<IllegalArgumentException> { Tower(60.0, 800.0, -1.0, 15.0, 5) }
 }

 @Test
 fun constructor_ascentSpeedHigherThanDescentSpeed_IllegalArgumentException() {
  assertThrows<IllegalArgumentException> { Tower(60.0, 800.0, 20.0, 15.0, 5) }
 }

 @Test
 fun constructor_descentSpeedLowerThan0_IllegalArgumentException() {
  assertThrows<IllegalArgumentException> { Tower(60.0, 800.0, 2.0, -1.0, 5) }
 }

 @Test
 fun constructor_descentSpeedEqualTo0_IllegalArgumentException() {
  assertThrows<IllegalArgumentException> { Tower(60.0, 800.0, 2.0, 0.0, 5) }
 }

 @Test
 fun constructor_waitingTimeLowerThan0_IllegalArgumentException() {
  assertThrows<IllegalArgumentException> { Tower(60.0, 800.0, 2.0, 15.0, -1) }
 }

 @Test
 fun constructor_waitingTimeEqualTo0_IllegalArgumentException() {
  assertThrows<IllegalArgumentException> { Tower(60.0, 800.0, 2.0, 15.0, 0) }
 }

 @Test
 fun constructor_allParametersMeetRequirements_objectCreated() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(60.0, tower.maxHeight)
  assertEquals(800.0, tower.maxWeight)
  assertEquals(2.0, tower.ascentSpeed)
  assertEquals(15.0, tower.descentSpeed)
  assertEquals(5, tower.waitingTime)
  assertEquals(0.0, tower.currentHeight)
  assertEquals(TowerState.LOADING, tower.currentState)
  assertEquals(false, tower.isLocked)
 }


 /**** Setters ****/

 @Test
 fun setAscentSpeed_valueLowerThan0_IllegalArgumentException() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertThrows<IllegalArgumentException> { tower.ascentSpeed = -1.0 }
 }

 @Test
 fun setAscentSpeed_valueHigherThanDescentSpeed_IllegalArgumentException() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertThrows<IllegalArgumentException> { tower.ascentSpeed = 16.0 }
 }

 @Test
 fun setAscentSpeed_valueMeetsRequirements_valueChanged() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  tower.ascentSpeed = 1.5

  assertEquals(1.5, tower.ascentSpeed)
 }

 @Test
 fun setDescentSpeed_valueLowerThan0_IllegalArgumentException() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertThrows<IllegalArgumentException> { tower.descentSpeed = -1.0 }
 }

 @Test
 fun setDescentSpeed_valueEqualTo0_IllegalArgumentException() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertThrows<IllegalArgumentException> { tower.descentSpeed = 0.0 }
 }

 @Test
 fun setDescentSpeed_valueMeetsRequirements_valueChanged() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  tower.descentSpeed = 20.0

  assertEquals(20.0, tower.descentSpeed)
 }

 @Test
 fun setWaitingTime_valueLowerThan0_IllegalArgumentException() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertThrows<IllegalArgumentException> { tower.waitingTime = -1 }
 }

 @Test
 fun setWaitingTime_valueEqualTo0_IllegalArgumentException() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertThrows<IllegalArgumentException> { tower.waitingTime = 0 }
 }

 @Test
 fun setWaitingTime_valueMeetsRequirements_valueChanged() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  tower.waitingTime = 10

  assertEquals(10, tower.waitingTime)
 }

 @Test
 fun setIsLocked_valueMeetsRequirements_valueChanged() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  tower.isLocked = true

  assertEquals(true, tower.isLocked)
 }

 /**** Getters ****/

 @Test
 fun getMaxHeight_valueReturned() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(60.0, tower.maxHeight)
 }

 @Test
 fun getMaxWeight_valueReturned() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(800.0, tower.maxWeight)
 }

 @Test
 fun getAscentSpeed_valueReturned() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(2.0, tower.ascentSpeed)
 }

 @Test
 fun getDescentSpeed_valueReturned() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(15.0, tower.descentSpeed)
 }

 @Test
 fun getWaitingTime_valueReturned() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(5, tower.waitingTime)
 }

 @Test
 fun getCurrentHeight_valueReturned() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(0.0, tower.currentHeight)
 }

 @Test
 fun getCurrentState_valueReturned() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(TowerState.LOADING, tower.currentState)
 }

 @Test
 fun getIsLocked_valueReturned() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(false, tower.isLocked)
 }


 /**** Functions ****/

 @Test
 fun updateStatus_secondsLowerThan0_IllegalArgumentException() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertThrows<IllegalArgumentException> { tower.updateStatus(-1) }
 }

 @Test
 fun updateStatus_towerIsNotLocked_stateLoadingAndHeightEqualTo0() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  tower.updateStatus(5)

  assertEquals(TowerState.LOADING, tower.currentState)
  assertEquals(0.0, tower.currentHeight)
 }

 @Test
 fun updateStatus_towerIsLockedAndAscending_stateAscentAndHeightIncreases() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)
  tower.isLocked = true

  tower.updateStatus(5)

  assertEquals(TowerState.ASCENT, tower.currentState)
  assertEquals(10.0, tower.currentHeight)
 }

 @Test
 fun updateStatus_towerIsLockedAndWaiting_stateWaitingAndHeightIsMax() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)
  tower.isLocked = true

  val ascentTime = (tower.maxHeight / tower.ascentSpeed).toInt()
  tower.updateStatus(ascentTime + 1)

  assertEquals(TowerState.WAITING, tower.currentState)
  assertEquals(60.0, tower.currentHeight)
 }

 @Test
 fun updateStatus_towerLockedAndDropping_stateDropAndHeightDecreases() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)
  tower.isLocked = true

  val ascentTime = (tower.maxHeight / tower.ascentSpeed).toInt()
  val dropSecond = ascentTime + tower.waitingTime + 1

  tower.updateStatus(dropSecond)

  assertEquals(TowerState.DROP, tower.currentState)
  assertEquals(tower.maxHeight - tower.descentSpeed, tower.currentHeight)
 }

 @Test
 fun safetyCheck_weightLowerThanMax_trueReturned() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(true, tower.safetyCheck(500.0))
 }

 @Test
 fun safetyCheck_weightHigherThanMax_falseReturned() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertEquals(false, tower.safetyCheck(900.0))
 }

 @Test
 fun safetyCheck_weightLowerThan0_IllegalArgumentException() {
  val tower = Tower(60.0, 800.0, 2.0, 15.0, 5)

  assertThrows<IllegalArgumentException> { tower.safetyCheck(-1.0) }
 }
}
