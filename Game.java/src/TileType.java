enum TileType {
    WALL {
        @Override
        public boolean isAccessibleBy(Form playerForm) {
            return false;
        }
    },

    NEUTRAL {
        @Override 
        public boolean isAccessibleBy(Form playerForm) {
            return true;
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
            return playerForm == Form.GREY;
        }
    },
    EXIT {
        @Override 
        public boolean isAccessibleBy(Form playerForm) {
            return playerForm == Form.GREY;
        }
    };
    public abstract boolean isAccessibleBy(Form playerForm);
}
