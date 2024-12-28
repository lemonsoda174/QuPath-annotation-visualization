from PIL import Image
Image.MAX_IMAGE_PIXELS = None

# to execute this code, modify the path to your corresponding images as shown below
# then run

# Path to image you want to convert
input_path = "C://Users//ADMIN//Desktop//export//test_5.png"
# Path to image you want to receive after the conversion
output_path = "C://Users//ADMIN//Desktop//export//test_5_palette.png"







def quantizetopalette(silf, palette, dither=False):
    """Convert an RGB, RGBA or L mode image to use a given P image's palette."""

    silf.load()
    palette.load()

    if palette.mode != "P":
        raise ValueError("already in palette mode")

    #if image in mode X, you may consider adding silf.mode != "X" to extend. it can possibly work!
    if silf.mode != "RGB" and silf.mode != "L" and silf.mode != "RGBA":
        raise ValueError(
            "only RGB, RGBA or L mode images can be quantized to a palette"
            )

    im = silf.im.convert("P", 1 if dither else 0, palette.im)
    # the 0 above means turn OFF dithering

    try:
        return silf._new(im)
    except AttributeError:
        return silf._makeself(im)

palettedata = [255, 255, 255, 0, 128, 0, 255, 143, 204, 255, 0, 0, 0, 0, 0, 165, 42, 42, 0, 0, 255]

NUM_ENTRIES_IN_PILLOW_PALETTE = 256
num_bands = len("RGB")
num_entries_in_palettedata = len(palettedata) // num_bands
palettedata.extend(palettedata[:num_bands]
                * (NUM_ENTRIES_IN_PILLOW_PALETTE
                    - num_entries_in_palettedata))
print(len(palettedata))


# Create a palette image whose size does not matter
arbitrary_size = 16, 16
palimage = Image.new('P', arbitrary_size)
palimage.putpalette(palettedata)

# Perform the conversion

oldimage = Image.open(input_path)

print(oldimage.mode)

newimage = quantizetopalette(oldimage, palimage, dither=False)
newimage.convert("P", palette=Image.ADAPTIVE, colors=8)

print(newimage.mode)
print(newimage.getpalette())

newimage.save(output_path, format = "PNG", dpi = (96,96), bits = 8)
