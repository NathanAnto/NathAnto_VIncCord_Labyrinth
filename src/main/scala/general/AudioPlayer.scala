package general

import javax.sound.sampled.{AudioSystem, Clip}

class AudioPlayer(path: String) {

  var audioClip: Clip = null
  try {
    // Create audio input URL
    val url = this.getClass.getClassLoader.getResource(path)
    val audioStream = AudioSystem.getAudioInputStream(url)
    // Obtain clip
    audioClip = AudioSystem.getClip.asInstanceOf[Clip]
    audioClip.open(audioStream)
  } catch {
    case e: Exception =>
      e.printStackTrace()
  }


  def play(): Unit = {
    // Open stream and play
    try {
      if (!audioClip.isOpen) audioClip.open()
      audioClip.stop()
      audioClip.setMicrosecondPosition(0)
      audioClip.start()
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }

  def stop(): Unit = {
    // stop
    try {
      if (audioClip.isOpen) audioClip.stop()
      audioClip.stop()
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }


}