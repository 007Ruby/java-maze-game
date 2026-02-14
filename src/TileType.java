enum TileType {
    WALL {
        @Override
        public boolean isAccessibleBy(Form playerForm) {
            return false;
        }
        @Override
        public boolean isDeadlyFor(Form playerForm) {
            return false;
        }
    },
    BLACK {
        @Override
        public boolean isAccessibleBy(Form playerForm) {
            return true;
        }
        @Override
        public boolean isDeadlyFor(Form playerForm) {
            if (playerForm != Form.BLACK) return true;
            return false;
        }
    },
    WHITE {
        @Override
        public boolean isAccessibleBy(Form playerForm) {
            return true;
        }
        @Override
        public boolean isDeadlyFor(Form playerForm) {
            if (playerForm != Form.WHITE) return true;
            return false;
        }
    },
    GREY {
        @Override
        public boolean isAccessibleBy(Form playerForm) {
            return true;
        }
        @Override
        public boolean isDeadlyFor(Form playerForm) {
            return false;
        }
    },
    EXIT {
        @Override 
        public boolean isAccessibleBy(Form playerForm) {
            return playerForm == Form.GREY;
        }
        @Override
        public boolean isDeadlyFor(Form playerForm) {
            return false;
        }
    };
    public abstract boolean isAccessibleBy(Form playerForm);
    public abstract boolean isDeadlyFor(Form playerForm);
    public TileType getTileType() {
        return this;
    };
}
