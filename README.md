## Getting Started
This project is a grid-based puzzle game built in Java using JavaFX. It demonstrates object-oriented design, frame-driven rendering, and data-driven level loading. The sections below describe the structure and architecture of the project.
## Game Overview
The player begins each level as a merged grey entity and can split into two separate forms: white and black. Each form can only traverse tiles of its corresponding color along with neutral tiles. The objective is to collect all shards and reach the exit while merged.
## Core gameplay rules:
 - White player can access white and neutral tiles
 - Black player can access black and neutral tiles
 - Walls block movement
 - All shards must be collected before exiting
 - The player must be merged to use the exit
 - Completing the final level displays a full completion message
## Architecture
The project follows a clear separation of responsibilities:
 - GameController manages high-level flow and level transitions
 - LevelManager handles level progression and determines whether the current level is the final level
 - GameState represents a single level instance and contains grid, player, shards, key, and win condition logic
 - GameView handles rendering and UI updates for a single GameState
 - GameState is fully independent and does not know about level progression.
## Rendering System
Rendering is frame-driven using AnimationTimer.
The update pipeline is structured into stages:
 - updateLogicalState()
 - updateRenderTargets()
 - interpolate()
 - applyToNodes()
This separates tile-based game logic from pixel-based render state. Movement between tiles is smoothly interpolated rather than instant.
## Data-Driven Level System
Levels are externalized into text files and parsed at runtime. This removes hardcoded level classes and separates engine logic from level content.
Each level file defines:
 - Grid dimensions
 - Tile layout
 - Player start position
 - Shard positions and forms
 - Key requirement
New levels can be added without modifying source code.
## Technical Highlights
 - Object-oriented design
 - Separation of concerns
 - Frame-driven rendering loop
 - Smooth interpolation animations
 - Event-listener pattern between GameState and GameView
 - Data-driven level architecture
## Purpose
This project was built to demonstrate scalable system design, clean architectural separation, and thoughtful handling of rendering versus game logic.
