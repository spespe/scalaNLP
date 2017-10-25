package nlp.main

/**
  * Created by pietro.speri on 19/12/2016.
  */

object scalanlp extends App {
  //Sentence
  val sentence =
  //""":50F:/BE30001216371411;1/PHILIPS MARK;4/19720830;5/BE/BRUSSELS"""
  //""":50F:DRLC/BE/BRUSSELS/NB0949042;1/DUPONT JACQUES;2/HIGH STREET 6, APT 6C;3/BE/BRUSSELS"""
  //"""textunusefulblablabla:50F:CUST/DE/ABC BANK/123456789/8-123456;1/MANN GEORG;2/LOW STREET 7;3/DE/FRANKFURT;8/firstAdditional;8/secondAdditional"""
  //"|50A|/123456789;1/HSBCUK33|56"
  //"saf|50K|/122267890; BIODATA GMBH; HOCHSTRASSE, 27; 8022-ZURICH; SWITZERLAND|34"
  //"|50K|/987654321;Big Deal Real Estate;Madison Avenue;New York, NY USA|16"

  //Parse method used to parse the input string and match the content
  def parse(str: String) = {
    val sep = ";"
    val line = str.replaceAll("\\|[0-9]{1,}$","")
    //Regexp
    val pattern50A = "^.{0,}(\\|50A\\|)".r
    val matching50A = pattern50A.findFirstIn(line)
    val pattern50F = "^.{0,}(\\|50F\\|)".r //to be fixed
    val matching50F = pattern50F.findFirstIn(line)
    val pattern50K = "^.{0,}(\\|50K\\|)".r
    val matching50K = pattern50K.findFirstIn(line)
    val pattern59A = "^.{0,}(\\|59A\\|)".r
    val matching59A = pattern59A.findFirstIn(line)
    val pattern59F = "^.{0,}(\\|59F\\|)".r
    val matching59F = pattern59F.findFirstIn(line)

    //Field 50A
    matching50A match {
      case Some(s) => {
        val arr50A = line.replace(s, "").split(sep)
        val account = arr50A.filter(x => x.startsWith("/")) mkString (" ")
        val identifier_code = arr50A.filter(x => !x.startsWith("/")).map(x=>x.trim) mkString (" ")
        println("50A,Account:" + account + ",Identifier:" + identifier_code)
      }
      case None => ""
    }

    //Field 50F
    matching50F match {
      case Some(s) if (line.replace(s, "").split(sep)(0).startsWith("/")) => {
        val arr50F = line.replace(s, "").split(sep)
        val account = arr50F(0)
        val name = arr50F.filter(x => x.startsWith("1/")).map(x => x.trim.replaceAll("^(1/)", "")) mkString (" ")
        val address = arr50F.filter(x => x.startsWith("2/")).map(x => x.trim.replaceAll("^(2/)", "")) mkString (" ")
        val country_town = arr50F.filter(x => x.startsWith("3/")).map(x => x.trim.replaceAll("^(3/)", "")) mkString (" ")
        val dob = arr50F.filter(x => x.startsWith("4/")).map(x => x.trim.replaceAll("^(4/)", "")) mkString (" ")
        val pob = arr50F.filter(x => x.startsWith("5/")).map(x => x.trim.replaceAll("^(5/)", "")) mkString (" ")
        val cin = arr50F.filter(x => x.startsWith("6/")).map(x => x.trim.replaceAll("^(6/)", "")) mkString (" ")
        val nin = arr50F.filter(x => x.startsWith("7/")).map(x => x.trim.replaceAll("^(7/)", "")) mkString (" ")

        println("50F," + "Account: " + account + ",Name: " + name + ",Address: " + address + ",Country/Town: " + country_town + ",Date of birth: " + dob +
          ",Place of birth: " + pob + ",Customer identification number: " + cin + ",National identity number: " + nin)
      }
      case Some(s) if (!line.replace(s, "").split(sep)(0).startsWith("/")) => {
        val arr50F = line.replace(s, "").split(sep)
        val additional = arr50F.filter(x => x.startsWith("8/")).map(x => x.trim.replaceAll("^8/", "")) mkString (" ")
        val identifier = arr50F(0)
        var identif = ""
        val start = identifier.substring(0, 4)

        start match {
          case "ARNU" => identif = "Alien Registration Number: " + identifier
          case "CCPT" => identif = "Passport Number: " + identifier
          case "CUST" => identif = "Customer Identification: " + identifier
          case "DRLC" => identif = "Driver's License Number: " + identifier
          case "EMPL" => identif = "Employer Number: " + identifier
          case "NIDN" => identif = "National Identity Number: " + identifier
          case "SOSE" => identif = "Social Security Number: " + identifier
          case "TXID" => identif = "Tax Identification Number: " + identifier
        }

        val name = arr50F.filter(x => x.startsWith("1/")).map(x => x.trim.replaceAll("^(1/)", "")) mkString (" ")
        val address = arr50F.filter(x => x.startsWith("2/")).map(x => x.trim.replaceAll("^(2/)", "")) mkString (" ")
        val country_town = arr50F.filter(x => x.startsWith("3/")).map(x => x.trim.replaceAll("^(3/)", "")) mkString (" ")
        val dob = arr50F.filter(x => x.startsWith("4/")).map(x => x.trim.replaceAll("^(4/)", "")) mkString (" ")
        val pob = arr50F.filter(x => x.startsWith("5/")).map(x => x.trim.replaceAll("^(5/)", "")) mkString (" ")
        val cin = arr50F.filter(x => x.startsWith("6/")).map(x => x.trim.replaceAll("^(6/)", "")) mkString (" ")
        val nin = arr50F.filter(x => x.startsWith("7/")).map(x => x.trim.replaceAll("^(7/)", "")) mkString (" ")

        //adding additional field 8
        var nin_final, cin_final, identif_final = ""
        if (!nin.isEmpty && start != "NIDN"){nin_final = nin + additional}else{nin_final = nin}
        if (!cin.isEmpty && start != "CUST"){cin_final = cin + additional}else{cin_final = cin}
        if (nin_final == nin && cin_final == cin){identif_final = identif + additional}

        println("50F," + identif_final + ",Name: " + name + ",Address: " + address + ",Country/Town: " + country_town + ",Date of birth: " + dob +
          ",Place of birth: " + pob + ",Customer identification number: " + cin_final + ",National identity number: " + nin_final)
      }
      case None =>
    }

    //Field 50K
    matching50K match {
      case Some(s) if(line.replace(s, "").split(sep)(0).startsWith("/")) => {
        val arr50K = line.replace(s, "").split(sep).toList
        val identifier = arr50K(0).trim
        val start = identifier.substring(0,5)
        var identif = ""
        val name = arr50K(1).trim

        start match {
          case "/ARNU" => identif = "Alien Registration Number: " + identifier
          case "/CCPT" => identif = "Passport Number: " + identifier
          case "/CORP" => identif = "Corporate Identification: " + identifier
          case "/DRLC" => identif = "Driver's License Number: " + identifier
          case "/OTHR" => identif = "Other identification: " + identifier
          case "/TXID" => identif = "Tax Identification Number: " + identifier
          case _ => identif = "Account: " + identifier
        }

        //address
        if(arr50K.size>2){
          //val wordsArr=arr50K(2)
          //new things to implement depending on the data and the way in which we want to proceed, for now I created a dictionary for countries
        }

        val address = if(arr50K.size>2 && arr50K(2).matches("[0-9]")){arr50K(2)}

        println("50K,"+identif+",Name: "+name+",Address: "+address)
      }
      case None =>
    }

    //Field 59A
    matching59A match {
      case Some(s) => {
        val arr59A = line.replace(s, "").split(sep)
        val account = arr59A.filter(x => x.startsWith("/")).map(x=>x.trim) mkString (" ")
        val bic = arr59A.filter(x => !x.startsWith("/")).map(x=>x.trim) mkString (" ")
        println("59A," + "Account: " + account + ",Bic: " + bic)
      }
      case None =>
    }

    //Field 59F
    matching59F match {
      case Some(s) => {
        val arr59F = line.replace(s, "").split(sep)
        val account = arr59F.filter(x => x.startsWith("/")) mkString (" ")
        val name = arr59F.filter(x => x.startsWith("1/")).map(x => x.trim.replaceAll("^(1/)", "")) mkString (" ")
        val address = arr59F.filter(x => x.startsWith("2/")).map(x => x.trim.replaceAll("^(2/)", "")) mkString (" ")
        val country_town = arr59F.filter(x => x.startsWith("3/")).map(x => x.trim.replaceAll("^(3/)", "")) mkString (" ")
        println("59F,"+"Account: "+account+",Name: "+name+",Address: "+address+",Country/town: "+country_town)
      }
      case None =>
    }
  }

  parse(sentence)
}

