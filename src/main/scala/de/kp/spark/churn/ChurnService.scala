package de.kp.spark.churn
/* Copyright (c) 2014 Dr. Krusche & Partner PartG
* 
* This file is part of the Spark-Churn project
* (https://github.com/skrusche63/spark-churn).
* 
* Spark-Churn is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free Software
* Foundation, either version 3 of the License, or (at your option) any later
* version.
* 
* Spark-Churn is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with
* Spark-Churn. 
* 
* If not, see <http://www.gnu.org/licenses/>.
*/

import akka.actor.{ActorSystem,Props}
import com.typesafe.config.ConfigFactory

import de.kp.spark.churn.actor.ChurnMaster

/**
 * ChurnService is an Akka Remoting based Churn Prediction Service
 */
object ChurnService {

  def main(args: Array[String]) {
    
    val name:String = "churn-server"
    val conf:String = "server.conf"

    val server = new ChurnService(conf, name)
    while (true) {}
    
    server.shutdown
      
  }

}

class ChurnService(conf:String, name:String) {

  val system = ActorSystem(name, ConfigFactory.load(conf))
  sys.addShutdownHook(system.shutdown)

  val master = system.actorOf(Props[ChurnMaster], name="churn-master")

  def shutdown = system.shutdown()
  
}