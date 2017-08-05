package nlp.main

import com.aliasi.chunk.RegExChunker

/**
  * Created by pietro.speri on 12/12/2016.
  */

//class account {
//
//
//  class acc extends RegExChunker(TIME_RE:String,String,Double) {
//    def AccountChunker() {
//      (this.TIME_RE, CHUNK_TYPE, CHUNK_SCORE)
//    }
//    //val TIME_RE = "\\/[0-9]{9}"
//    //val CHUNK_TYPE = "account"
//    //final val CHUNK_SCORE = 1.0
//  }
//}

//if(line.split(";").size==1){identifier=line.split(";")(0).replaceAll("^.+[509]{2}[AFK]{1}:","")}
//println("50A,Account:"+account+",Identifier BIC:"+identifier)
//Account
//val ACC_RE = "\\/[0-9]{9}"
//val ACC_CHUNK_TYPE = "account"
//val ACC_CHUNK_SCORE = 1.0
//var accountField=""
////Chunker
//val account_chunker = new RegExChunker(ACC_RE, ACC_CHUNK_TYPE, ACC_CHUNK_SCORE)
//Splitting on separator
//s.split(";").foreach{x=>
//  val acc_chunking = account_chunker.chunk(x)
//  val set = acc_chunking.chunkSet
//  val acc_iterator = set.iterator
//  while (acc_iterator.hasNext) {
//    val next = acc_iterator.next
//    //val chk = next.toString.replaceAll("^.+:", "").replaceAll("@.+$", "")
//    val start = next.start
//    val end = next.end
//    accountField = x.substring(start, end)
//  }
//}
//println("50A,Account:"+accountField+",Identifier:")


