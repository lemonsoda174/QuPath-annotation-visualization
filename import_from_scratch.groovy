//QP 0.2.3 compliant


// set the directory path of the folder containing the image you want to import
def directoryPath = 'C://Users//ADMIN//Desktop//export'


// set the downscale factor you previously used to export your image. e.g. here you downsized your original image by 64 times smaller
double prev_downscale_ratio = 64
ImagePlane plane = ImagePlane.getDefaultPlane()

File folder = new File(directoryPath);
File[] listOfFiles = folder.listFiles();

// set the name of the image you want to import
currentImport = listOfFiles.find{ it.getName().equals("test_1_palette.jpg") }

path = currentImport.getPath()

def imp = IJ.openImage(path)

// set the name list of annotations
def classificationMask = ['Viable tumor', 'Necrosis', 'Fibrosis/Hyalination', 'Hemorrhage/Cystic change', 'Inflammatory', 'Non-tumor tissue'] 
def annotations = []

// set the number of annotations available. should be equal to (length of annotation list - 1)
int num_labels = 7

def ip = imp.getProcessor()
print(ip.getStatistics())
def roisIJ = RoiLabeling.labelsToConnectedROIs(ip, num_labels)
    print(roisIJ)
    def rois = roisIJ.collect {
        if (it == null)
            return
        return IJTools.convertToROI(it, 0, 0, prev_downscale_ratio, plane);
        }
    print(rois.size())
//  rois = rois.findAll{null != it}
    print(rois.size())
    print(rois)
    
    for (i = 0; i < rois.size() + 1; i++) {
       roi = rois[i]
       classificationRoi = classificationMask[i]

       roi.collect {
      
          if(it == null) {
             return 
          }else {
              anno = PathObjects.createAnnotationObject(it,getPathClass(classificationRoi))
              annotations << anno          
          }      
       }
    }
    addObjects(annotations)
    

resolveHierarchy()
print "Import completed"

import ij.gui.Wand
import qupath.lib.objects.PathObjects
import qupath.lib.regions.ImagePlane
import ij.IJ
import ij.process.ColorProcessor
import qupath.imagej.processing.RoiLabeling
import qupath.imagej.tools.IJTools
import java.util.regex.Matcher
import java.util.regex.Pattern