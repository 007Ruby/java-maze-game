enum TileType {
    WALL {
        @Override
        public boolean isAccessibleBy(Form playerForm) {
            return false;
        }
    },
    BLACK {
        @Override
        public boolean isAccessibleBy(Form playerForm) {
            return playerForm == Form.BLACK;
        }
    },
    WHITE {
        @Override
        public boolean isAccessibleBy(Form playerForm) {
            return playerForm == Form.WHITE;
        }
    },
    GREY {
        @Override
        public boolean isAccessibleBy(Form playerForm) {
            return (playerForm == Form.GREY || playerForm == Form.BLACK || playerForm == Form.WHITE) ;
        }
    },
    EXIT {
        @Override 
        public boolean isAccessibleBy(Form playerForm) {
            return playerForm == Form.GREY;
        }
    };
    public abstract boolean isAccessibleBy(Form playerForm);
    public TileType getTileType() {
        return this;
    };
}
