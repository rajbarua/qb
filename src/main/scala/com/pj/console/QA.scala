package com.pj.console

sealed abstract class QA 

case class PAD() extends QA
case class Unsure() extends QA