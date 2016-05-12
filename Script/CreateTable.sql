CREATE TABLE `lego`.`BrickSet` (
  `SetID` VARCHAR(16) NOT NULL,
  `Number` VARCHAR(16) NULL,
  `Variant` VARCHAR(8) NULL,
  `Theme` VARCHAR(128) NULL,
  `SubTheme` VARCHAR(128) NULL,
  `Year` VARCHAR(8) NULL,
  `Name` VARCHAR(128) NULL,
  `Minifigs` VARCHAR(8) NULL,
  `Pieces` VARCHAR(8) NULL,
  `PriceUK` VARCHAR(16) NULL,
  `PriceUS` VARCHAR(16) NULL,
  `PriceCA` VARCHAR(16) NULL,
  `PriceEU` VARCHAR(16) NULL,
  `ImageURL` VARCHAR(256) NULL,
  PRIMARY KEY (`SetID`));

CREATE TABLE `lego`.`BrickSetInventory` (
  `SetNumber` VARCHAR(16) NOT NULL,
  `PartID` VARCHAR(16) NOT NULL,
  `Quantity` VARCHAR(8) NULL,
  `Colour` VARCHAR(128) NULL,
  `Category` VARCHAR(128) NULL,
  `DesignID` VARCHAR(16) NULL,
  `PartName` VARCHAR(128) NULL,
  `ImageURL` VARCHAR(256) NULL,
  `SetCount` VARCHAR(8) NULL,
  PRIMARY KEY (`SetNumber`, `PartID`));
