#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_LSM303_U.h>
#include <Adafruit_L3GD20_U.h>
#include <Adafruit_9DOF.h>

/* Assign a unique ID to the sensors */
Adafruit_LSM303_Accel_Unified accel = Adafruit_LSM303_Accel_Unified(30301);

void setup(void)
{
  Serial.begin(9600);
  accel.begin();
}

void loop(void)
{
  /* Get a new sensor event */
  sensors_event_t event;
   
  /* Display the results (acceleration is measured in m/s^2) */
  accel.getEvent(&event);
  Serial.print(event.acceleration.x); Serial.print(",");
  Serial.print(event.acceleration.y); Serial.print(",");
  Serial.print(event.acceleration.z); Serial.println();
  delay(100);
}
