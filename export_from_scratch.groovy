def imageData = getCurrentImageData()

// Define output path (relative to project)
def outputDir = buildFilePath("C://Users//ADMIN//Desktop", 'export')
mkdirs(outputDir)
// output image
def path = buildFilePath(outputDir, "labels.png")

// Define how much to downsample during export (may be required for large images)
double downsample = 8

// Create an ImageServer where the pixels are derived from annotations
def labelServer = new LabeledImageServer.Builder(imageData)
  .backgroundLabel(0, ColorTools.WHITE) // Specify background label (usually 0 or 255)
  .downsample(downsample)    // Choose server resolution; this should match the resolution at which tiles are exported


  // modify this manually, in the order you want
  .addLabel('Viable tumor', 1)      // Choose output labels (the order matters!)
  .addLabel('Necrosis', 2)
  .addLabel('Fibrosis/Hyalination', 3)
  .addLabel('Hemorrhage/Cystic change', 4)
  .addLabel('Inflammatory', 5)
  .addLabel('Non-tumor tissue', 6)


  .multichannelOutput(false) // If true, each label refers to the channel of a multichannel binary image (required for multiclass probability)
  .build()

// Write the image
writeImage(labelServer, path)