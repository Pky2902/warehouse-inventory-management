# Warehouse Inventory Management System

An Android application concept for managing warehouse stock locally on a
mobile device. The project report describes a native Android
architecture using **Java**, **XML layouts**, and an embedded **SQLite**
database.

## Overview

The Warehouse Inventory Management System is designed to help users
maintain product records and track stock levels without requiring a
continuous connection to a centralized ERP system. Its local-first
design focuses on data persistence, input validation, quick inventory
searches, and safe stock-management operations.

> **Project documentation:** This README summarizes the architecture and
> specifications described in the project report. It does not, by
> itself, confirm that every proposed feature or performance target has
> been implemented or tested.

## Key Features and Design Goals

-   Maintain product and stock information in a local database.
-   Add inventory items with product details and initial stock
    quantities.
-   Display inventory records in a catalog-style list.
-   Search inventory using product information.
-   Highlight low-stock conditions.
-   Validate user input before database operations.
-   Require confirmation before destructive actions such as deleting a
    record.
-   Keep database operations off the main UI thread to help maintain a
    responsive interface.
-   Provide a potential path to future cloud synchronization.

## Technology Stack

  -----------------------------------------------------------------------
  Component                           Technology / Specification
  ----------------------------------- -----------------------------------
  Platform                            Android

  Language                            Java

  UI                                  XML layouts and Material Design
                                      components

  Local storage                       SQLite

  Architecture                        Model--View--Controller (MVC) with
                                      a Data Access Object (DAO) pattern

  Minimum Android API in the report   API 26 (Android 8.0 Oreo)

  Target tier described in the report API 34 (Android 14)

  Database helper                     `SQLiteOpenHelper`
  -----------------------------------------------------------------------

## Architecture

The design separates the application into three main layers:

### Model / Data Layer

-   Uses a DAO-style approach to isolate database access.
-   Stores inventory records in a local SQLite database.
-   Applies database constraints and transaction handling.

### View Layer

-   Uses XML layouts for application screens.
-   Presents inventory records in a reusable list.
-   Uses Material Design principles for layout, color, and interaction
    feedback.

### Controller Layer

-   Android activities coordinate user actions and application flow.
-   The report identifies `LoginActivity` and
    `InventoryDashboardActivity` as example activities.
-   Background execution is intended for database work to reduce UI
    blocking.

## Main Screens

1.  **Login screen** --- provides a user authentication entry point.
2.  **Inventory dashboard** --- provides product entry controls and an
    inventory catalog.
3.  **Catalog item view** --- displays item details and stock levels,
    with visual warnings for low stock.

## Database Design

The report describes a local database named `WarehouseCore.db` and a
core table named `table_inventory_core`.

  -------------------------------------------------------------------------
  Column                    Type                    Purpose
  ------------------------- ----------------------- -----------------------
  `item_id`                 `INTEGER`               Auto-incrementing
                                                    primary key

  `product_sku`             `TEXT`                  Unique product
                                                    identifier

  `product_name`            `TEXT`                  Product name

  `current_stock_level`     `INTEGER`               Current stock quantity;
                                                    defaults to `0`

  `unit_valuation`          `REAL`                  Unit valuation / cost

  `product_description`     `TEXT`                  Optional product
                                                    description

  `epoch_timestamp_added`   `INTEGER`               Timestamp when the
                                                    record was added
  -------------------------------------------------------------------------

The schema uses a unique constraint on `product_sku` and requires values
for key product fields. The report also discusses transaction handling
and indexing to support reliable data changes and faster lookups.

## Validation and Data Safety

The design includes the following safeguards:

-   Check required product fields before saving.
-   Validate stock quantities as non-negative whole numbers.
-   The report specifies a maximum stock quantity of `1,000,000`.
-   Use database constraints to help prevent invalid or duplicate
    records.
-   Wrap writes in transactions so a database operation can be completed
    or rolled back safely.
-   Ask for confirmation before deleting records.
-   Run database operations asynchronously rather than blocking the main
    UI thread.

## UI Design

The report references Material Design 3 principles and the following
color tokens:

  Purpose                        Color               Hex
  ------------------------------ ------------------- -----------
  Primary actions                Deep forest green   `#1B5E20`
  Errors / destructive actions   Crimson red         `#D32F2F`
  Main background                Off-white           `#FAFAFA`
  Secure-access background       Near black          `#121212`

The design also calls for scalable typography using `sp` and layout
dimensions using `dp`.

## Future Improvements

The report identifies these possible extensions:

-   Synchronization with a centralized system using secure REST APIs.
-   Network communication through libraries such as Retrofit or OkHttp.
-   Reactive database observation and UI updates.
-   Migration to Android Jetpack Room for a more modern persistence
    layer.
-   Further testing of performance, accessibility, and data recovery
    behavior.

## Getting Started

This repository README is based on the project report. To run the
application, the actual Android Studio project source code, build
configuration, and setup instructions must be available in the
repository.

Once the source project is added:

1.  Open the project in Android Studio.
2.  Allow Gradle to sync.
3.  Confirm the configured Android SDK and build tools are installed.
4.  Run the app on an emulator or Android device.

## Project Report

The project was documented as **Warehouse Inventory Management System**
for a B.Sc. in Software Development.

## References

The report refers to these technical resources and standards:

-   Android Open Source Project (AOSP) architecture guidance.
-   SQLite database engine documentation.
-   Material Design 3 design system documentation.

------------------------------------------------------------------------

**Note:** Specifications and future extensions above are summarized from
the project report. Update this README to match the final source code
and tested behavior before presenting it as a completed implementation.
