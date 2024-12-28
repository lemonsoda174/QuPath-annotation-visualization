# QuPath-annotation-visualization
Small project for importing and exporting QuPath annotations as full labeled images

# How to use
1. Add the groovy scripts to QuPath's "Automate" functionality
2. Start using by following these steps:
   - For exports: After creating annotations in QuPath, you can simply run the "export_from_scratch" script to get a full labeled image of the annotations.
   - For imports: If image already in compatible format (Image MUST be in Palette mode "P" with the correct given palette to import), simply run the "import_from_scratch" script to import annotations back into QuPath for modifications. If image is not in compatible format, you can change its format through using "convert_to_palette" python file. The image will become compatible for import as usual
  
   Notes:
   You should modify the downsampling factors to your liking, change directory paths and set labels in QuPath's "annotations" section so that it is in accordance with all the files provided before imports.
