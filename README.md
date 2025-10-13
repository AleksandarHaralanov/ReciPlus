# ReciPlus
**ReciPlus** is a Minecraft plugin designed for servers running version b1.7.3.

It allows server operators to add custom crafting and furnace recipes with high customizability.

---
## Contributing Code & Reporting Issues
Consider helping ReciPlus become even more versatile and robust.

Visit the [CONTRIBUTING](https://github.com/AleksandarHaralanov/ReciPlus/blob/master/.github/CONTRIBUTING.md) guide for details on how to get started and where to focus your efforts.

For any issues with the plugin, or suggestions, please report them [here](https://github.com/AleksandarHaralanov/ReciPlus/issues).

---
## Download
Latest releases of **ReciPlus** can be found here on [GitHub](https://github.com/AleksandarHaralanov/ReciPlus/releases).<br>

The plugin is fully open-source and transparent.<br>
If you'd like additional peace of mind, you're welcome to scan the `.jar` file using [VirusTotal](https://www.virustotal.com/gui/home/upload).

---
## Requirements
Your b1.7.3 server must be running one of the following APIs: CB1060-CB1092, [Project Poseidon](https://github.com/retromcorg/Project-Poseidon) or [UberBukkit](https://github.com/Moresteck/Project-Poseidon-Uberbukkit).

---
## Usage
By default, only OPs have permission.

Use PermissionsEx or similar plugins to grant groups the permission, enabling the commands.

### Commands:
| Command | Permission | Description |
|---------|------------|-------------|
| `/rp` | None | View ReciPlus commands. |
| `/rp about` | None | About ReciPlus. |
| `/rp reload` | `reciplus.config` | Reload ReciPlus configuration. |

### Permissions:
| Permission | Description |
|------------|-------------|
| `reciplus.*` | Wildcard permission granting everything. |
| `reciplus.config` | Allows player to reload the ReciPlus configuration. |

---
## Configuration
Generates empty configuration files `shaped.yml`, `shapeless.yml`, and `furnace.yml` located at `ReciPlus/recipes`.

> [!CAUTION]
> It is **very** important to follow these layout patterns exactly as they are.
> 
> **Double quotes:** Use them exactly where shown. "X" is used for IDs to ensure proper parsing.
> 
> **No quotes:** When no quotes are present, it indicates a plain number, like 1, which YAML handles as an integer.
> 
> *Incorrectly following this pattern can cause the plugin to break entirely or result in improper recipes.*

See the templates below, followed by a deeper explanation of the IDs and how you can use metadata.

### Setting up custom recipes
```yaml
shaped:
  r1:            # rX - X being the number of the recipe. Always start from 1 and increment by 1 accordingly
    result:
      id: "X"    # ID of the result item
      amount: X  # Amount of the result item (1-64)
    grid:        # Grid can be 1x1, 1x2, 1x3, 2x2, 2x3, and 3x3
      - "***"    # If your recipe specifically needs air (nothing) somewhere, then use '*'
      - "***"    # And 'X' (replace X with a char that isn't '*') for any respective ingredient below
      - "***"
    ingredients:
      - "X;X"    # Left is assigned character for ingredient - Right is ID of that ingredient
      - ...      # Optional: Multiple ingredients
  rX:
    ...
```
```yaml
shapeless:
  r1:                        # rX - X being the number of the recipe. Always start from 1 and increment by 1 accordingly
    result:
      id: "X"                # ID of the result item
      amount: X              # Amount of the result item (1-64)
    ingredients: ["X", ...]  # ID of ingredient(s) - You must have a minimum of 1 and a maximum of up to 9; duplicates are allowed
  rX:
    ...
```
```yaml
furnace:
  r1:            # rX - X being the number of the recipe. Always start from 1 and increment by 1 accordingly
    result:
      id: "X"    # ID of the result item
      amount: X  # Amount of the result item (1-64)
    source: "X"  # ID of the source item
  rX:
    ...
```
> [!TIP]
> When writing IDs, you can include metadata if desired, as long as the item can support metadata.
> 
> For example, `"35:14"` represents Red Wool, whereas `"35"`, or `"35:0"`, represents White Wool.
> 
> Click [here](assets/Items.png) for a list of all Minecraft b1.7.3 IDs.

### Disabling custom recipe types
If you do not wish to use a certain type of recipe in this plugin, navigate to `ReciPlus/recipes/recipe.yml`, clear the configuration, and put the following inside:
```yaml
shaped: []
```
```yaml
shapeless: []
```
```yaml
furnace: []
```
> [!NOTE] 
> Do **not** delete the configuration files. Instead, use the aforementioned method to disable them.
