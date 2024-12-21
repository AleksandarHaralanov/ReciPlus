# ReciPlus
## What's ReciPlus?
**ReciPlus** is a Minecraft plugin designed for servers running version b1.7.3. It allows server operators to add custom crafting and furnace recipes with high customizability.

### Download
Latest releases of **ReciPlus** can be found here on [GitHub](https://github.com/AleksandarHaralanov/ReciPlus/releases).<br>
Alternatively, you can also download through [Modrinth](https://modrinth.com/plugin/ReciPlus/versions).

The plugin is fully open-source and transparent. If you'd like additional peace of mind, you're welcome to scan the `.jar` file using [VirusTotal](https://www.virustotal.com/gui/home/upload).

### Requirements
Your b1.7.3 server must be running one of the following APIs: CB1060-CB1092, [Project Poseidon](https://github.com/retromcorg/Project-Poseidon) or [UberBukkit](https://github.com/Moresteck/Project-Poseidon-Uberbukkit).



### Usage
By default, only OPs have permission.

Use PermissionsEx or similar plugins to grant groups the permission, enabling the commands.

#### Commands
- `/rp` - View ReciPlus commands.
- `/rp about` - About ReciPlus.
- `/rp reload` - `reciplus.config` - Reload all ReciPlus recipe configs.

#### Permissions:
##### Single permissions:
- `reciplus.reload` - Allows player to reload the ReciPlus recipe configs.
##### Wildcard permissions:
- `reciplus.*` - Wildcard permission granting everything. (Currently only `reciplus.reload`)

### Configuration
Automatically generates configuration files `shaped.yml`, `shapeless.yml`, and `furnace.yml` located at `plugins/ReciPlus/recipes`.

Recipe layout configuration patterns:
```yaml
shaped:
  r1:            # rX - X being the number of the recipe. Always start from 1 and increment by 1 accordingly
    result:
      id: "X"    # ID of the result item
      amount: X  # Amount of the result item (1-64)
    grid:        # 3x3 Grid (* for nothing - Character to assign to an ingredient)
      - "***"
      - "***"
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
  r1:               # rX - X being the number of the recipe. Always start from 1 and increment by 1 accordingly
    result:
      id: "X"       # ID of the result item
      amount: X     # Amount of the result item (1-64)
    source: "X"  # ID of the source item
  rX:
    ...
```
> [!NOTE]
> When writing IDs, include metadata if needed. For example, use "35:14" for Red Wool, whereas "35"/"35:0" represents plain White Wool.
> Click [here](assets/Items.png) for a list of all Minecraft b1.7.3 IDs.
---
To disable a specific recipe type, navigate to `ReciPlus/recipes/recipe.yml`, clear the configuration, and put the following inside:
```yaml
shaped: []
```
```yaml
shapeless: []
```
```yaml
furnace: []
```
> [!CAUTION] Do NOT delete the configuration files!